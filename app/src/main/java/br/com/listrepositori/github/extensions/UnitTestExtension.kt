package br.com.listrepositori.github.extensions


import org.junit.Assert.assertEquals
import org.junit.Assert.assertThat
import org.hamcrest.CoreMatchers.instanceOf


infix fun <T> T.assertInstanceOf(clazz: Class<*>) {
    assertThat(this, instanceOf(clazz))
}