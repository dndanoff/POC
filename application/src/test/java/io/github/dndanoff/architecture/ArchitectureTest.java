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
	
	private static final String ROOT_PACKAGE = "cars.ship.shipperlite.posting";
	
	static final String ALL_PACKAGES = ROOT_PACKAGE+"..";
	
	static final String DOMAIN_LAYER_PACKAGES = ROOT_PACKAGE+".domain..";
	
	static final String APPLICATION_LAYER_PACKAGES = ROOT_PACKAGE+".application..";
	 
	static final String CONFIG_PACKAGES = ROOT_PACKAGE+".config..";

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
                .importPackages(ALL_PACKAGES);
        
        log.info("Finished loading classes");
    }
}