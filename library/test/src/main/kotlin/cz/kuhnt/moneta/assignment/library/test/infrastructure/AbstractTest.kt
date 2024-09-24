package cz.kuhnt.moneta.assignment.library.test.infrastructure

import org.junit.Rule

abstract class AbstractTest {

    @get:Rule
    val coroutinesDispatchersRule = CoroutinesDispatchersRule()
}
