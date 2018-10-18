package com.fenbi.newbull.hellotest;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.jdbc.core.JdbcOperations;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

/**
 * @author fankai
 */
@RunWith(MockitoJUnitRunner.class)
public class MockitoTest {

    public static class Register {
        private int num;

        public void set(int n) {
            this.num = n;
        }

        public int get() {
            return num;
        }

        public int multiplied(int t) {
            return num * t;
        }
    }

    @Mock
    JdbcOperations db;

    @Spy
    Register register;

    @Test
    public void testQuery() {
        when(db.queryForList(anyString(), eq(Integer.class))).thenReturn(Arrays.asList(1, 2, 3));
        List<Integer> ids = db.queryForList("SELECT id FROM order_item", Integer.class);
        verify(db, times(1)).queryForList(anyString(), eq(Integer.class));
    }

    @Test
    public void testCalc() {
        register.set(5);
        Assert.assertThat(register.multiplied(2), is(10));
        when(register.multiplied(anyInt())).thenAnswer((Answer<Integer>) invocation -> {
            final int argument = (int)(invocation.getArguments())[0];
            return argument * 10;
        });
        Assert.assertThat(register.multiplied(2), is(20));
        verify(register, never()).get();

        doThrow(new RuntimeException()).when(register).set(anyInt());
//        register.set(3);
    }


}