/*
 * Copyright 2012-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.tdmehmet.catalog;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

import com.tdmehmet.catalog.domain.Account;
import com.tdmehmet.catalog.repository.AccountRepository;

/**
 * 
 * Configures and starts spring application.
 * By commandlinerunner we insert new account to our restdb.
 * New account could also be inserted on import.sql but 
 * there may be a need to hash the password in the future. 
 * 
 * 
 * @author Mehmet Tahir Dede
 *
 */

@Configuration
@ComponentScan
@EnableAutoConfiguration
@Import(RepositoryRestMvcConfiguration.class)
public class TdmehmetCatalogRestApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(TdmehmetCatalogRestApplication.class, args);
	}
	@Bean
    CommandLineRunner init(final AccountRepository accountRepository) {
      
      return new CommandLineRunner() {

        @Override
        public void run(String... arg0) throws Exception {
          accountRepository.save(new Account("usertest", "secret"));
        }
      };
    }
}
