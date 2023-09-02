package opekope2.optigui.filter

/**
 * A filter, which succeeds when the given value is [expectedValue], fails otherwise, and never skips.
 *
 * @param T The type the filter accepts
 * @param expectedValue The value the filter should succeed for
 */
class EqualityFilter<T>(private val expectedValue: T) : IFilter<T, Unit> {
    override fun evaluate(value: T): IFilter.Result<out Unit> =
        if (value == expectedValue) IFilter.Result.match(Unit) else IFilter.Result.mismatch()

    override fun toString(): String = "${javaClass.name}, expected value: $expectedValue"
}
