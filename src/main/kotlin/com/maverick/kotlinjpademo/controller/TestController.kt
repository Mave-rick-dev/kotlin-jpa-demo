package com.maverick.kotlinjpademo.controller

import com.maverick.kotlinjpademo.DTO.ClientSchemeDTO
import com.maverick.kotlinjpademo.DTO.ClientSchemeServiceDTO
import com.maverick.kotlinjpademo.DTO.MasterServiceDTO
import com.maverick.kotlinjpademo.model.ClientScheme
import com.maverick.kotlinjpademo.model.ClientSchemeService
import com.maverick.kotlinjpademo.model.MasterService
import com.maverick.kotlinjpademo.repo.ClientSchemeRepo
import com.maverick.kotlinjpademo.repo.ClientSchemeServiceRepo
import com.maverick.kotlinjpademo.repo.MasterServiceRepo
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.lang.IllegalArgumentException
import java.util.*
import javax.websocket.server.PathParam
import kotlin.collections.ArrayList


/*
import com.maverick.kotlinjpademo.repo.ClientSchemeServiceRepo
*/

/**
 * @Project kotlin-jpa-demo
 * @Author mave on 1/3/22
 */
@RestController
@RequestMapping("/api/")
class TestController(
    var clientSchemeRepo: ClientSchemeRepo,
    var masterServiceRepo: MasterServiceRepo,
    var clientSchemeServiceRepo: ClientSchemeServiceRepo
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
        var clientSchemeL: MutableList<ClientScheme> = clientSchemeRepo.findAll()
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
        //save when clientSchemeServiceDTO.id == null
        if(clientSchemeServiceDTO.id == null){
            val newClientScheme: ClientScheme =
                clientSchemeRepo.findById(clientSchemeServiceDTO.clientSchemeId).get()

            val newClientSchemeServiceL: ArrayList<ClientSchemeService> = arrayListOf()

            masterServiceRepo.findAllById(clientSchemeServiceDTO.masterServiceIds).stream()
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
        }else{
            return ResponseEntity("Create operation does not require " + clientSchemeServiceDTO.id + " !!", HttpStatus.BAD_REQUEST)
        }
    }
}

