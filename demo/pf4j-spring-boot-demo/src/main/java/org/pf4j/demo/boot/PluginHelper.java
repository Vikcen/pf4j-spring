package org.pf4j.demo.boot;

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.util.List;

import javax.annotation.PostConstruct;

import org.pf4j.PluginManager;
import org.pf4j.PluginWrapper;
import org.pf4j.demo.boot.api.Greeting;
import org.pf4j.demo.boot.util.ContextWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class PluginHelper {
	@Value("${pf4j.pluginsDir}")
	private String pluginsDir;

    @Autowired
    private ApplicationContext context;

    @Autowired
    private PluginManager pluginManager;
    
    @Autowired
    private Greetings greetings;

    public static int counter;

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println(event);
    }

    @PostConstruct
    public void loadingPlugins() {
    	System.setProperty("pf4j.pluginsDir", pluginsDir);
    	FileSystem fileSystem = FileSystems.getDefault();

        // Ensure the context will be initialized
        ContextWrapper.setContext(context);
        // Loading plugins
        //List<Greeting> greetings = pluginManager.getExtensions(Greeting.class); //doesn't work
        
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(PluginConfiguration.class);
        PluginManager pluginManager = applicationContext.getBean(PluginManager.class);
        try {
			//pluginManager.loadPlugins();
			List<Greeting> greetings = pluginManager.getExtensions(Greeting.class);
			System.out.println("\nLOAD ALL PLUGINS VIA getExtensions METHOD FROM AN INTERFAZ CLASS");
			System.out.println("greetings.size() = " + greetings.size());
			for (Greeting greeting : greetings) {
			    System.out.println(greeting.getGreeting());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

        
        
        try {
        	//PluginManager pluginManager = new SpringPluginManager(sistemaFicheros.getPath(pluginsDir));
		    PluginWrapper pluginWrapper = pluginManager.getPlugin("id-greeting-plugins");
		    pluginWrapper.getPluginManager().startPlugins();
		    System.out.println("\nLOAD PLUGINS VIA ID JAR");
		    System.out.println(pluginWrapper.getPluginState());
		    System.out.println("greetings.size() = " + pluginWrapper.getPluginManager().getExtensions(Greeting.class).size());
		    for (Greeting greeting : pluginWrapper.getPluginManager().getExtensions(Greeting.class)) {
		        System.out.println(greeting.getGreeting());
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        try {
			System.out.println("\nLOAD PLUGINS VIA AUTOWIRED LIST");
			// retrieves automatically the extensions for the Greeting.class extension point
			Greetings greetings = applicationContext.getBean(Greetings.class);
			greetings.printGreetings();
			System.out.println("LOAD PLUGINS VIA AUTOWIRED LIST: OK");
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
