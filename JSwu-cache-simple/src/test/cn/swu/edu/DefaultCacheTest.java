package cn.swu.edu;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;

/**
 * Created by 西南大学开源协会 陈思定  on 2017/8/27.
 * <p>
 * Email : sidingchan@gmail.com
 */
public class DefaultCacheTest {


    private DefaultCache cache;
    private Foo one;
    private Foo one2;
    private Foo two;

    @Before
    public void before(){
        cache = new DefaultCache();
        one = new Foo(1);
        one2 = new Foo(1);
        two = new Foo(2);
    }
    @Test
    public void testPut(){
        clearCache();
        assertEquals(cache.size(),0);
        cache.putCache(one.hashCode(),one);
        assertEquals(cache.size(),1);
        cache.putCache(two.hashCode(),two);
        assertEquals(cache.size(),2);
    }

    @Test
    public void testGet(){
        clearCache();
        Object obj1 = cache.getCache(-1);
        assertNull(obj1);

        cache.putCache(one.hashCode(),one);
        cache.putCache(one2.hashCode(),one2);
        Object cone = cache.getCache(one.hashCode());
        Object cone2 = cache.getCache(one2.hashCode());

        assertEquals(one,cone);
        assertEquals(one2,cone2);
        assertNotEquals(cone,cone2);
        assertNotEquals(one,one2);

    }

    @Test
    public void testRemoveAll(){
        clearCache();
        cache.putCache(one.hashCode(),one);
        cache.putCache(one2.hashCode(),one2);
        cache.putCache(two.hashCode(),two);
        assertEquals(cache.size(),3);
        cache.removeAll();
        assertEquals(cache.size(),0);
    }

    @Test
    public void testRemove(){
        clearCache();

        cache.putCache(one.hashCode(),one);
        cache.putCache(one2.hashCode(),one2);
        cache.putCache(two.hashCode(),two);

        // test remove() just remove one element
        cache.remove(one.hashCode());
        Object obj = cache.getCache(one.hashCode());
        assertEquals(obj,null);
        assertEquals(cache.size(),2);

        // test remove() will not change other element
        Object obj2 = cache.getCache(one2.hashCode());
        Object obj3 = cache.getCache(two.hashCode());

        assertEquals(obj2,one2);
        assertEquals(obj3,two);

    }
    private void clearCache(){
        cache.removeAll();
    }
}
