package com.maverick.kotlinjpademo.controller

import com.maverick.kotlinjpademo.DTO.ClientSchemeDTO
import com.maverick.kotlinjpademo.DTO.ClientSchemeServiceDTO
import com.maverick.kotlinjpademo.DTO.MasterServiceDTO
import com.maverick.kotlinjpademo.exceptionHandler.GlobalApiResponse
import com.maverick.kotlinjpademo.mapper.ClientSchemeServiceMapper
import com.maverick.kotlinjpademo.model.ClientScheme
import com.maverick.kotlinjpademo.model.ClientSchemeService
import com.maverick.kotlinjpademo.model.MasterService
import com.maverick.kotlinjpademo.repo.ClientSchemeRepo
import com.maverick.kotlinjpademo.repo.ClientSchemeServiceRepo
import com.maverick.kotlinjpademo.repo.MasterServiceRepo
import org.hibernate.MappingException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.websocket.server.PathParam


/**
 * @Project kotlin-jpa-demo
 * @Author mave on 1/3/22
 */
@RestController
@RequestMapping("/api/")
class SchemeController(
    var clientSchemeRepo: ClientSchemeRepo,
    var masterServiceRepo: MasterServiceRepo,
    var clientSchemeServiceRepo: ClientSchemeServiceRepo,
    var clientSchemeServiceMapper: ClientSchemeServiceMapper
) {

    //Master service
    @GetMapping("/get-master-services")
    fun getAllMasterServices(): ResponseEntity<Any> {
        return ResponseEntity.ok(masterServiceRepo.findAll())
    }

    @PostMapping("/create-master-service")
    fun createMasterService(@RequestBody masterServiceDTO: MasterServiceDTO): ResponseEntity<Any> {

        val masterService = MasterService()

        with(masterService) {
            isActive = masterServiceDTO.isActive
            serviceCode = masterServiceDTO.serviceCode
            description = masterServiceDTO.description
            isAvailable = masterServiceDTO.isAvailable

            return ResponseEntity.ok(masterServiceRepo.save(masterService))
        }
    }

    @PostMapping("/delete-master-service")
    fun deleteMasterService(@PathParam("masterServiceId") masterServiceId: Int): ResponseEntity<Any> {
        return ResponseEntity.ok(masterServiceRepo.deleteById(masterServiceId))
    }

    //Client scheme
    @GetMapping("/get-client-schemes")
    fun getAllClientSchemes(): ResponseEntity<Any> {
        return ResponseEntity.ok(clientSchemeRepo.findAll())
    }

    @PostMapping("/create-client-scheme")
    fun createClientSchemes(@RequestBody clientSchemeReq: ClientSchemeDTO): ResponseEntity<Any> {

        val clientScheme = ClientScheme()

        with(clientScheme) {
            isActive = clientSchemeReq.isActive
            name = clientSchemeReq.name
            tranAmountLimit = clientSchemeReq.tranAmountLimit
            tranLimit = clientSchemeReq.tranLimit
        }
        return ResponseEntity.ok(clientSchemeRepo.save(clientScheme))
    }

    //Client scheme service
    @PostMapping("/create-client-scheme-service")
    fun createClientSchemeService(@RequestBody clientSchemeServiceDTO: ClientSchemeServiceDTO): ResponseEntity<Any> {

        if (clientSchemeServiceDTO.id == null) {
            //validating clientSchemeId
            val newClientScheme: ClientScheme =
                clientSchemeRepo.findById(clientSchemeServiceDTO.clientSchemeId)
                    .orElseThrow { NoSuchElementException("Client schemeId ${clientSchemeServiceDTO.clientSchemeId} does not exist!!") }

            //validating masterServiceIds
            val newClientSchemeServiceL: ArrayList<ClientSchemeService> = arrayListOf()
            val validMasterServiceIds: ArrayList<Int> = arrayListOf()
            val invalidMasterServiceIds: ArrayList<Int> = arrayListOf()

            clientSchemeServiceDTO.masterServiceIds.stream()
                .forEach {
                    if (masterServiceRepo.findById(it).isPresent) {
                        validMasterServiceIds.add(it)
                    } else {
                        invalidMasterServiceIds.add(it)
                    }
                }
            if (invalidMasterServiceIds.isNotEmpty()) {
                throw NoSuchElementException("Master service id: $invalidMasterServiceIds are invalid!!")
            }

            //validating existing master service(s) of clientSchemeid from reqDTO
            val existingMasterServiceIds: List<Int> =
                clientSchemeServiceMapper.getClientSchemes(clientSchemeServiceDTO.clientSchemeId)

            existingMasterServiceIds.forEach { existingId ->
                validMasterServiceIds.forEach { validId ->
                    if (existingId == validId) {
                        invalidMasterServiceIds.add(validId)
                    }
                }
            }
            if (invalidMasterServiceIds.isNotEmpty()) {
                throw MappingException(
                    "Master service id: $invalidMasterServiceIds are already mapped to Client Scheme Id: " +
                            "${clientSchemeServiceDTO.clientSchemeId}!!"
                )
            }
            masterServiceRepo.findAllById(validMasterServiceIds).stream()
                .distinct()
                .forEach {
                    val clientSchemeService = ClientSchemeService()
                    with(clientSchemeService) {
                        limitValidation = clientSchemeServiceDTO.limitValidation
                        tranAmountLimit = clientSchemeServiceDTO.tranAmountLimit
                        tranLimit = clientSchemeServiceDTO.tranLimit
                        performView = clientSchemeServiceDTO.performView
                        performTransaction = clientSchemeServiceDTO.performTransaction
                        selfTransactionOnly = clientSchemeServiceDTO.selfTransactionOnly
                        clientScheme = newClientScheme
                        masterService = it
                    }
                    newClientSchemeServiceL.add(clientSchemeService)
                }
            return ResponseEntity(clientSchemeServiceRepo.saveAll(newClientSchemeServiceL), HttpStatus.CREATED)
        } else {
            throw IllegalArgumentException("Create operation does not require key: id!!")
        }
    }
}

