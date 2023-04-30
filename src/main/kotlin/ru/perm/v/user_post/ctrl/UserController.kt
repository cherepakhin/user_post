package ru.perm.v.user_post.ctrl

import org.springframework.web.bind.annotation.*
import ru.perm.v.user_post.dto.UserDto
import ru.perm.v.user_post.service.UserService

/**
 * Rest-контроллер пользователей.
 * Замечание: Контроллер обращается напрямую к слою Service и выполняет не только конвертацию в DTO,
 * но и некоторые внутренние операции, ктр. д.б. в отдельном промежуточном слое. Сделано для простоты.
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

    @PostMapping("/")
    fun createUser(@RequestBody userDto: UserDto): UserDto {
        val existingUser = userService.getById(userDto.id)
        if (existingUser != null) {
            userService.deleteById(userDto.id)
        }
        val u = userService.create(userDto.id, userDto.name, userDto.email)
        return UserDto(u.id, u.name, u.email)
    }

    @PutMapping("/{id}")
    fun updateUser(@PathVariable id: Long, @RequestBody userDto: UserDto): UserDto {
//        val existingUser = userRepository.findById(id).orElseThrow { EntityNotFoundException("User not found with id $id") }
//        existingUser.name = userDto.name
//        existingUser.email = userDto.email
//        return UserDto(userRepository.save(existingUser).id, existingUser.name, existingUser.email)
        return UserDto(userDto.id, userDto.name, userDto.email)
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Long) {
        userService.deleteById(id)
    }
}
