package com.mongo.controller;

import com.mongo.bean.CacheStudent;
import com.mongo.bean.CacheTeacher;
import com.mongo.service.CacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cache")
@RestController
@Slf4j
@EnableCaching //开启缓存的功能
public class CacheController {

    @Autowired
    CacheService cacheService;

    /*
    * 缓存自动配置原理
    * 1.自动配置类:CacheAutoConfiguration
    * 2.自动配置类中通过方法:CacheConfigurationImportSelector加载了各种自动配置
    * org.springframework.boot.autoconfigure.cache.GenericCacheConfiguration
    * org.springframework.boot.autoconfigure.cache.EhCacheCacheConfiguration
    * org.springframework.boot.autoconfigure.cache.RedisCacheConfiguration
    * org.springframework.boot.autoconfigure.cache.SimpleCacheConfiguration
    *
    * 可以通过debug=true,从自动配置报告中查看哪些自动配置类起作用了哪些自动配置类没有起作用
    *
    * 3.默认为:SimpleCacheConfiguration生效
    * 4.SimpleCacheConfiguration给容器中注册了一个ConcurrentMapCacheManager类型的cacheManager
    * 5.cacheManager的作用:可以获取和创建ConcurrentMap类型的缓存组件,它的作用是将数据保存在ConcurrentMap中
    *
    * */
    //这里相当于定义变量id
//    @RequestMapping("/testCacheable/{id}") //访问路径是/testCacheable/20?name=zhaokun
    //@PathVariable注解从访问路径中获取参数,相当于获取变量id
    //@RequestParam注解获取请求的参数,如果请求参数的名字与形参变量的名字一样,可以忽略不写,如果不一样则必须得写
//    public CacheStudent testCacheable(@PathVariable("id") Integer id,@RequestParam(value = "name") String name) {
//        log.info("id = " + id);
//        log.info("name = " + name);
//        return cacheService.testCacheable();
//    }

//    @RequestMapping("/testCacheable")
//    @Cacheable(value = "student")
//    @Cacheable(value = {"student","teacher"})
//    public CacheStudent testCacheable(Integer id) {
//
//        log.info("id = " + id);
//        return cacheService.testCacheable();
//    }

    /*
    * @Cacheable注解:
    * @Cacheable可以标记在一个方法上,也可以标记在一个类上。当标记在一个方法上时表示该方法支持缓存。当
    * 标记在一个类上时则表示该类中所有的方法都支持缓存。
    * 对于一个支持缓存的方法,Spring会在其被调用后将其返回值缓存起来,以保证下次利用相同的参数来执行该方法时
    * 可以直接从缓存中获取结果,而不需要再次执行该方法。Spring在缓存方法的返回值是以键值对进行缓存的,值就是
    * 方法的返回值,键值Spring支持两种策略:默认的策略和自定义的策略
    *
    * value/cacheNames:这两个代表的是相同的意思,并且该属性必须指定,表示当前方法的返回值是被缓存在哪个Cache上的,对于Cache的名称
    * ,其可以是一个Cache也可以是多个Cache,当需要指定多个Cache其是一个数组
    * 一个的表示形式:@Cacheable(value = "student")
    * 多个数组的表示形式:@Cacheable(value = {"student","teacher"})
    *
    * key:缓存数据使用的key,可以用它来指定,默认是使用方法参数的值,参照示例:testCacheable-testCacheable7
    * keyGenerator:自定义生成key的策略
    * key与keyGenerator二者选择一个进行配置
    *
    * cacheManager/cacheResolver执行缓存管理器和解析器
    *
    * condition:当符合条件时才进行缓存
    *
    * unless:否定缓存,当unless指定的条件为true,方法返回的结果不会被缓存,可以通过获取到的结果进行判断
    *
    * sync:是否采用异步模式
    * */

    //将对象的属性作为key值,使用的是spEL表达式
    @RequestMapping("/testCacheable")
     @Cacheable(value = "student",key = "#s.id")
    public CacheStudent testCacheable(@RequestBody CacheStudent s) {

        log.info("id = " + s.getId());
        log.info("name = " + s.getName());
        return cacheService.testCacheable();
    }

    //测试使用不同参数,key值采用默认值(如果有1个参数的情况)
    @RequestMapping("/testCacheable1")
    @Cacheable(value = "student")
    public CacheStudent testCacheable1(int id) {

        log.info("id = " + id);
        return cacheService.testCacheable();
    }

    //测试使用不同参数,key值采用默认值(如果有多个参数的情况,spring会将多个参数进行组合,可以查看ConcurrentMapCache类中的lookup方法查看key的值)
    @RequestMapping("/testCacheable2")
    @Cacheable(value = "student")
    public CacheStudent testCacheable2(int id,String name) {

        log.info("id = " + id);
        log.info("name = " + name);
        return cacheService.testCacheable();
    }

    @RequestMapping("/testCacheable3")
//    @Cacheable(value = "student",key = "#root.args[0]") //使用请求参数中的第0个参数作为key值
    @Cacheable(value = "student",key = "#root.args[1]")
    public CacheStudent testCacheable3(int id,String name) {

        log.info("id = " + id);
        log.info("name = " + name);
        return cacheService.testCacheable();
    }

    //使用spEL表达式,将对象的各种属性进行组合
    @RequestMapping("/testCacheable4")
    @Cacheable(value = "student",key = "#s.id + '[' + #s.name + ']'")
    public CacheStudent testCacheable4(@RequestBody CacheStudent s) {

        log.info("id = " + s.getId());
        log.info("name = " + s.getName());
        return cacheService.testCacheable();
    }

    //使用keyGenerator,参照CacheConfig类,自定义了key值的生成策略
    @RequestMapping("/testCacheable5")
    @Cacheable(value = "student",keyGenerator = "UserKeyGenerator")
    public CacheStudent testCacheable5(@RequestBody CacheStudent s) {

        log.info("id = " + s.getId());
        log.info("name = " + s.getName());
        return cacheService.testCacheable();
    }

    //测试使用@Condition注解,该注解只是用于控制缓存是否执行而不是控制方法是执行
    @RequestMapping("/testCacheable6")
    @Cacheable(value = "student",key = "#s.id",condition = "#s.id < 10")
    public CacheStudent testCacheable6(@RequestBody CacheStudent s) {

        log.info("id = " + s.getId());
        log.info("name = " + s.getName());
        return cacheService.testCacheable();
    }

    //测试使用@unless注解,当条件成立时不进行缓存,当添加不成立时进行缓存
    @RequestMapping("/testCacheable7")
    //也可以对其输出值进行判断
    @Cacheable(value = "student",key = "#s.id",unless = "#s.id > 10")
    //可以对其返回结果进行判断,当返回结果为null时不对其进行缓存
//    @Cacheable(value = "student",key = "#s.id",unless = "#result == null")
    public CacheStudent testCacheable7(@RequestBody CacheStudent s) {

        log.info("id = " + s.getId());
        log.info("name = " + s.getName());
        return cacheService.testCacheable();
    }

    /*
    * @CachePut可以声明一个方法支持缓存功能,与@Cacheable不同的是@CachePut标注的方法在执行前不会去检查缓存中是否
    * 存在之前执行过的结果,而是每次都会执行该方法,并将结果以键值对的形式存入指定的缓存中
    *
    * @CachePut也可以标注在类和方法上,和@Cacheable是一样的
    *
    * 使用场景:修改了数据库中的数据,同时更新缓存
    *
    * */
    @RequestMapping("/testCachePut")
//    @CachePut(value = "student",key = "#s.id") //使用传入id作为key值然后进行缓存
    @CachePut(value = "student",key = "#result.id")
    public CacheStudent testCachePut(@RequestBody CacheStudent s) {

        log.info("id = " + s.getId());
        log.info("name = " + s.getName());

        CacheStudent ss = cacheService.testCacheable();
        return ss;
    }

    /*
    * @CacheEvict注解
    * @CacheEvicty是用来标注在需要清除缓存元素的方法或类上。当标记在一个类上时表示其中所有的方法执行
    * 都会触发缓存的清除操作
    *
    * key:表示需要清除的是哪个key,如果未指定则会使用默认策略生成key
    * @allEntries:allEntries是boolean类型,表示是否需要清除缓存中的所有元素。默认是false,表示不需要
    * 当指定了allEntries为true时,spring cache将忽略指定的key
    *
    * @beforeInvocation
    * 清除操作默认是在对应方法成功之后触发的,即方法如果因为抛出异常而未能成功返回也不会触发清除操作。使用
    * beforeInvocation可以改变触发清除操作的时间,当我们指定该属性值为true,spring会在调用该方法之前清除缓存中指定的元素
    *
    * */

    //测试清除指定的key
    @RequestMapping("/testCacheEvict")
    @CacheEvict(value = "student",key = "#s.id")
    public CacheStudent testCacheEvict(@RequestBody CacheStudent s) {

        log.info("id = " + s.getId());
        log.info("name = " + s.getName());

        CacheStudent ss = cacheService.testCacheable();
        return ss;
    }

    //测试移除所有的key
    @RequestMapping("/testCacheEvict1")
    @CacheEvict(value = "student",key = "#s.id",allEntries = true)
    public CacheStudent testCacheEvict1(@RequestBody CacheStudent s) {

        log.info("id = " + s.getId());
        log.info("name = " + s.getName());

        CacheStudent ss = cacheService.testCacheable();
        return ss;
    }

    /**
     * @Caching注解
     * @Caching注解可以让我们在一个方法或者类上同时指定多个Spring Cache相关的注解
     * 其拥有3个属性:
     * cacheable:指定@Cacheable
     * put:@CachePut
     * evict:@CacheEvit
     */
    @RequestMapping("/testCaching")
    @Caching(
            cacheable={
                    @Cacheable(value = "student",key="#s.id")
            },
            put={
                @CachePut(value = "student",key="#s.id")
            }
    )
    public CacheStudent testCaching(@RequestBody CacheStudent s) {

        log.info("id = " + s.getId());
        log.info("name = " + s.getName());

        CacheStudent ss = cacheService.testCacheable();
        return ss;
    }

    /*
    *   @CacheConfig是一个类级别的注解,允许共享缓存的名称、KeyGenerator、CacheManager 和CacheResolver
    *
    *   场景:有时候一个类中可能会有多个缓存操作,而这些缓存操作可能是重复的,这个时候就可以使用 @CacheConfig
    *
    *   @CacheConfig("books") //统一定义了缓存的名字为books,因此各个方法上缓存的名字就可以不用再写了
        public class Book {

            @Cacheable
            public Book findBook(ISBN isbn) {...}
}
    * */

    @RequestMapping("/testTecherCacheable")
    @Cacheable(value = "techer",key = "#s.id + 'techer' ")
    public CacheTeacher testRedis(@RequestBody CacheTeacher s) {

        log.info("id = " + s.getId());
        log.info("name = " + s.getName());

        CacheTeacher teacher = new CacheTeacher();

        teacher.setId(20);
        teacher.setName("ycl");

        return teacher;
    }

}
