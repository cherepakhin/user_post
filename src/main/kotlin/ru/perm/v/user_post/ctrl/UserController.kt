package ru.perm.v.user_post.ctrl

import org.springframework.web.bind.annotation.*
import ru.perm.v.user_post.exception.NotFoundEntityExcpt
import ru.perm.v.user_post.dto.UserDto
import ru.perm.v.user_post.service.UserService

/**
 * Rest-контроллер пользователей.
 * Замечание: Сделано для простоты!!! Только в учебных целях.
 * Контроллер обращается напрямую к слою Service и выполняет не только конвертацию в DTO,
 * но и некоторые внутренние операции, ктр. д.б. в отдельном промежуточном слое.
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
