package io.github.dndanoff.architecture;

import static com.tngtech.archunit.base.DescribedPredicate.not;
import static com.tngtech.archunit.core.domain.JavaClass.Predicates.belongToAnyOf;

import org.junit.jupiter.api.Test;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;

import io.github.dndanoff.school.domain.model.vo.Filter;

public class SimpleArchitectureTest extends ArchitectureTest {
	
	 @Test
	    public void testModelLayerSeparation() {
	        ArchRule rule = ArchRuleDefinition.classes()
	                .that().resideInAPackage(MODEL_LAYER_PACKAGES)
	                .should().onlyDependOnClassesThat()
	                .resideInAnyPackage(MODEL_LAYER_PACKAGES, "java..", "javax..", "org.springframework.util..", "org.springframework.data..", "org.hibernate..", "org.slf4j..", "org.apache.commons..");
	        
	        JavaClasses allExceptGenericFilter = classes.that(not(belongToAnyOf(Filter.class)));
	        rule.check(allExceptGenericFilter);
	    }
	    
	    @Test
	    public void testRepositoryLayerSeparation() {
	        ArchRule rule = ArchRuleDefinition.classes()
	                .that().resideInAPackage(REPOSITORY_LAYER_PACKAGES)
	                .should().onlyDependOnClassesThat()
	                .resideInAnyPackage(REPOSITORY_LAYER_PACKAGES, MODEL_LAYER_PACKAGES, "java..", "org.springframework.stereotype..", "org.springframework.data..");
	        rule.check(classes);
	    }
}