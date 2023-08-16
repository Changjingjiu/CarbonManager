package com.carbon;

import com.carbon.sys.entity.Warning;
import com.carbon.sys.mapper.WarningMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class CarbonApplicationTests {

    @Resource
    private WarningMapper warningMapper;

    @Test
    void contextLoads() {
        List<Warning> warnings = warningMapper.selectList(null);
        warnings.forEach(System.out::println);
    }

}
