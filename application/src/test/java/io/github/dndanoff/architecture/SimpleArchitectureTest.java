package io.github.dndanoff.architecture;

import org.junit.jupiter.api.Test;

import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;

public class SimpleArchitectureTest extends ArchitectureTest {
	
	@Test
    public void testDomainLayerSeparation() {
        ArchRule rule = ArchRuleDefinition.classes()
                .that().resideInAPackage(DOMAIN_LAYER_PACKAGES)
                .should().onlyDependOnClassesThat()
                .resideInAnyPackage(DOMAIN_LAYER_PACKAGES, 
			                		"java..",
			                		"javax..",
			                		"com.google.common.base..",
			                		"org.springframework.data..",
			                		"org.hibernate..",
			                		"org.slf4j..");
        
        rule.check(classes);
    }
}