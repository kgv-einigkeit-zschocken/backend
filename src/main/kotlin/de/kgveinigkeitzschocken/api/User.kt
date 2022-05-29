package de.kgveinigkeitzschocken.api

import de.kgveinigkeitzschocken.core.inject.di
import de.kgveinigkeitzschocken.db.entity.UserEntity
import de.kgveinigkeitzschocken.db.service.UserService
import io.ktor.http.HttpStatusCode
import io.ktor.resources.Resource
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.resources.delete
import io.ktor.server.resources.get
import io.ktor.server.resources.post
import io.ktor.server.resources.put
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.Routing
import kotlinx.serialization.Serializable
import org.kodein.di.instance

fun Route.userRouting() {
    val userService: UserService by di.instance()

    get<Users> {
        call.respond(
            HttpStatusCode.OK,
            userService.all().map(UserEntity::getResponse)
        )
    }

    post<Users.Create> {
        call.respond(
            HttpStatusCode.Created,
            userService.create(call.receive()).getResponse()
        )
    }

    get<Users.Id> { endpoint ->
        call.respond(
            HttpStatusCode.OK,
            userService.findByID(endpoint.id).getResponse()
        )
    }

    put<Users.Id> { endpoint ->
        call.respond(
            HttpStatusCode.OK,
            userService.findByID(endpoint.id).update(call.receive()).getResponse()
        )
    }

    delete<Users.Id> { endpoint ->
        call.respond(
            HttpStatusCode.NoContent,
            userService.findByID(endpoint.id).delete()
        )
    }

}

@Serializable
@Resource("/users")
class Users {
    @Serializable
    @Resource("create")
    class Create(val parent: Users = Users())

    @Serializable
    @Resource("{id}")
    class Id(val parent: Users = Users(), val id: Int)
}