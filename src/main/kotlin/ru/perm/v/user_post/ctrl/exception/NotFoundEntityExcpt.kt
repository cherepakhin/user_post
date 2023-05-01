package ru.perm.v.user_post.ctrl.exception

/** Исключение "Объект не найден.
 *
 *  Использование:
 * <pre><code>
 *  if (existingUser.id.equals(-1)) {
 *      throw NotFoundEntityExcpt(String.format("Not found user with %i", id))
 * </code></pre>
 */
class NotFoundEntityExcpt(message: String? = "NOT FOUND") : Exception(message) {
}