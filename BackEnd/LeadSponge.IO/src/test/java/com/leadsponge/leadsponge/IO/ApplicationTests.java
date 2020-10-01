package com.leadsponge.leadsponge.IO;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.leadsponge.IO.LeadSpongeApiApplication;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = LeadSpongeApiApplication.class)
@SpringBootTest(classes = { LeadSpongeApiApplication.class })
class ApplicationTests {

	@Test
	void contextLoads() {
	}

}
