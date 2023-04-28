package ru.perm.v.user_post.ctrl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import ru.perm.v.user_post.dto.UserDto
import ru.perm.v.user_post.entity.UserEntity
import ru.perm.v.user_post.service.UserService

@RestController
@RequestMapping("/users")
class UserController(private val userService: UserService) {

    @GetMapping("/")
    fun getUsers(): List<UserDto> {
        return userService.getAll()
    }

    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: Long): UserDto {
        return userService.getById(id)
    }

    @PostMapping("/")
    fun createUser(@RequestBody userDto: UserDto): UserDto {
        val existingUser = userService.getById(userDto.id)
        if(existingUser!=null) {
            userService.deleteById(userDto.id)
        }
        return userService.create(userDto.id, userDto.name, userDto.email);
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
