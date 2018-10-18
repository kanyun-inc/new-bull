/**
 * @(#)SampleTest.java, Jul 07, 2018.
 * <p>
 * Copyright 2018 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package newbull.unittest;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author fankai
 */
public class CollectionTest extends AbstractTest {

    // TODO: 定义需要的注解
    @Before
    public void before() {
        System.out.println("run before every test");
    }

    @After
    public void after() {
        System.out.println("run after every test");
    }

    @Test
    public void testAdd() {
        Collection<Integer> collection = createCollection();
        collection.add(3);
        collection.add(9);
        assert collection.size() == 2;
    }

    @Test
    public void testNothing() {
        System.out.println("test nothing");
    }

    private Collection<Integer> createCollection() {
        Collection<Integer> collection = new ArrayList<Integer>();
        // Collection<Integer> collection = // TODO 返回使用CollectionHandler代理的Collection，便于调试
        return collection;
    }

    public static void main(String... args) {
        new CollectionTest().run();
    }
}
