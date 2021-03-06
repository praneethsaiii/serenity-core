package net.thucydides.core.guice;

import com.google.inject.Module;
import com.google.inject.Injector;
import com.google.inject.Guice;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;

/**
 * Somewhere to hold the Guice injector.
 * There might be a better way to do this.
 */
public class Injectors {

    private static Map<String,Injector>  injectors = Collections.synchronizedMap(new HashMap<>());


    public static synchronized Injector getInjector() {
        return getInjector(new ThucydidesModule());
    }
    
    public static synchronized Injector getInjector(com.google.inject.Module module) {
        String moduleClassName = module.getClass().getCanonicalName();
        Injector injector = injectors.get(moduleClassName);
        if (injector == null) {
    		injector = Guice.createInjector(module);
    		injectors.put(moduleClassName, injector);
    	}
    	return injector;
    }
}