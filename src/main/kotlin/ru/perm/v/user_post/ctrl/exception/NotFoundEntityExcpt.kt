package ru.perm.v.user_post.ctrl.exception

/**
 * This is an example to show usage of HTML pre tag while code snippet formatting in Javadocs
 *
 * <pre>
 * public class Application(){
 *     List<Integer> nums = new ArrayList<>();
 * }
 *
 * </pre>
 */
class NotFoundEntityExcpt(message: String? = "NOT FOUND") : Exception(message) {
    /** Исключение "Объект не найден.
     *
     *  Использование:
     * <pre><code>
     *  if (existingUser.id.equals(-1)) {
     *      throw NotFoundEntityExcpt(String.format("Not found user with %i", id))
     * </code></pre>
     */

}