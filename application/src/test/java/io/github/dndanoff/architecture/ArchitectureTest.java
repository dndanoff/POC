package io.github.dndanoff.architecture;

import org.junit.jupiter.api.BeforeAll;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.core.importer.Location;

public abstract class ArchitectureTest {
	
	private static final Logger log = LoggerFactory.getLogger(ArchitectureTest.class);
	
	static final String CONFIG_PACKAGES = "io.github.dndanoff.config..";
    static final String MODEL_LAYER_PACKAGES = "io.github.dndanoff.model..";
    static final String REPOSITORY_LAYER_PACKAGES = "io.github.dndanoff.repository..";
    static final String REST_LAYER_PACKAGES = "io.github.dndanoff.entry_point.web.rest..";
    static final String FACADE_LAYER_PACKAGES = "io.github.dndanoff.facade..";
    static final String SERVICE_LAYER_PACKAGES = "io.github.dndanoff.service..";

    static JavaClasses classes;

	private static final ImportOption IGNORE_GENERATED_FILES = new ImportOption() {
	    @Override
	    public boolean includes(Location location) {
	        return !location.contains("/generated/"); // ignore any URI to sources that contains '/test/'
	    }
	};
    
    @BeforeAll
    public static void setUp() {
        classes = new ClassFileImporter()
                .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
                .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_ARCHIVES)
                .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_JARS)
                .withImportOption(IGNORE_GENERATED_FILES)
                .importPackages(CONFIG_PACKAGES,
		                		MODEL_LAYER_PACKAGES,
		                		REPOSITORY_LAYER_PACKAGES,
		                		REST_LAYER_PACKAGES,
		                		FACADE_LAYER_PACKAGES,
		                		SERVICE_LAYER_PACKAGES);
        
        log.info("Finished loading classes");
    }
}