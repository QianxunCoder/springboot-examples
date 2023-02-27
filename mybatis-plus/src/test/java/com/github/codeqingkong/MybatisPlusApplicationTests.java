package com.github.codeqingkong;

import com.github.codeqingkong.entity.UserDO;
import com.github.codeqingkong.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MybatisPlusApplicationTests {
	@Autowired
	private UserMapper userMapper;

	@Test
	void contextLoads() {
		System.out.println(("----- selectAll method test ------"));
//		List<UserDO> userList = userMapper.selectList(null);

//		Assert.assertEquals(5, userList.size());
//		userList.forEach(System.out::println);
		UserDO userDO = new UserDO();
		userDO.setName("LoganLuo");
		userDO.setAge(25);
		userDO.setEmail("logan.zw.luo@cn.ey.com");
		userDO.setJson("{\\\"conditionType\\\":2,\\\"conditions\\\":[{\\\"conditionType\\\":1,\\\"connect\\\":\\\"=\\\",\\\"left\\\":{\\\"value\\\":\\\"SalesTarget.contractType\\\",\\\"valueType\\\":\\\"Fact\\\"},\\\"right\\\":{\\\"value\\\":\\\"现款现货\\\",\\\"valueType\\\":\\\"Literal\\\"}},{\\\"conditionType\\\":1,\\\"connect\\\":\\\"=\\\",\\\"left\\\":{\\\"value\\\":\\\"SalesTarget.projectKind\\\",\\\"valueType\\\":\\\"Fact\\\"},\\\"right\\\":{\\\"value\\\":\\\"材料销售\\\",\\\"valueType\\\":\\\"Literal\\\"}},{\\\"conditionType\\\":1,\\\"connect\\\":\\\">\\\",\\\"left\\\":{\\\"value\\\":\\\"SalesTarget.salesPrice\\\",\\\"valueType\\\":\\\"Fact\\\"},\\\"right\\\":{\\\"value\\\":\\\"${Fact:SalesTarget.cashPrice}*1.01\\\",\\\"valueType\\\":\\\"Expression\\\"}}],\\\"connect\\\":\\\"and\\\"}");
		userMapper.insert(userDO);
	}

}
