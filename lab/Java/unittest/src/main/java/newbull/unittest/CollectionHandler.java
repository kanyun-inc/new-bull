/**
 * @(#)CollectionHandler.java, Jul 07, 2018.
 * <p>
 * Copyright 2018 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package newbull.unittest;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * TODO 实现一个动态代理，当target是一个Collection，且执行add()操作时，打印参数和操作前后的size()
 * @author fankai
 */
public class CollectionHandler implements InvocationHandler {

    private Object target;

    public CollectionHandler(Object object) {
        target = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        throw new NotImplementedException();
    }
}