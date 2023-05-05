package ru.perm.v.userpost.ctrl

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.perm.v.userpost.dto.UserDto
import ru.perm.v.userpost.exception.NotFoundEntityExcpt
import ru.perm.v.userpost.service.UserService

/**
 * Rest-контроллер пользователей.
 * Контроллер обращается напрямую к слою Service и выполняет не только конвертацию в DTO,
 * но и некоторые внутренние операции, ктр. д.б. в отдельном промежуточном слое.
 * Замечание: Сделано для простоты!!! Только в учебных целях.
 */
@RestController
@RequestMapping("/users")
class UserController(private val userService: UserService) {

    @GetMapping("/")
    fun getUsers(): List<UserDto> {
        return userService.getAll().map { UserDto(it.id, it.name, it.email) }
    }

    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: Long): UserDto {
        val u = userService.getById(id)
        return UserDto(u.id, u.name, u.email)
    }

    /**
     * Создание User
     */
    @PutMapping("/")
    fun createUser(@RequestBody userDto: UserDto): UserDto {
        val existingUser = userService.getById(userDto.id)
        userService.deleteById(userDto.id)
        val u = userService.create(userDto.name, userDto.email)
        return UserDto(u.id, u.name, u.email)
    }

    /**
     * Обновление User
     */
    @PostMapping("/")
    fun updateUser(@RequestBody userDto: UserDto): UserDto {
        val user = userService.getById(userDto.id)
        if (user.id.equals(-1L)) {
            throw NotFoundEntityExcpt(String.format("Not found user with %s", user.id))
        }
//            .findById(id).orElseThrow { EntityNotFoundException("User not found with id $id") }
        user.name = userDto.name
        user.email = userDto.email
        val updatedUser = userService.save(user)
        return UserDto(updatedUser.id, updatedUser.name, updatedUser.email)
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Long) {
        userService.deleteById(id)
    }
}
