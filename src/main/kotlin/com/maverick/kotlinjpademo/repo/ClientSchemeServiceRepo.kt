package com.maverick.kotlinjpademo.repo

import com.maverick.kotlinjpademo.model.ClientSchemeService
import org.springframework.data.jpa.repository.JpaRepository



interface ClientSchemeServiceRepo : JpaRepository<ClientSchemeService, Int> {
}
