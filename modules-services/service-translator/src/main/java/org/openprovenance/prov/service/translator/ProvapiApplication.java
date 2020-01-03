
package org.openprovenance.prov.service.translator;

 
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import javax.ws.rs.core.Application;
import javax.ws.rs.ApplicationPath;

import org.jboss.resteasy.plugins.interceptors.CorsFilter;
import org.openprovenance.prov.interop.InteropFramework;
import org.openprovenance.prov.redis.RedisDocumentResource;
import org.openprovenance.prov.redis.RedisDocumentResourceIndex;
import org.openprovenance.prov.redis.RedisTemplateResourceIndex;
import org.openprovenance.prov.service.core.*;
import org.openprovenance.prov.service.core.memory.DocumentResourceIndexInMemory;
import org.openprovenance.prov.service.translation.TemplateResource;
import org.openprovenance.prov.service.translation.TemplateService;
import org.openprovenance.prov.service.translation.TranslationService;

import io.swagger.v3.jaxrs2.integration.resources.AcceptHeaderOpenApiResource;
import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.ServerVariable;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.info.Info;
import org.openprovenance.prov.service.translation.VisService;
import org.openprovenance.prov.service.translation.memory.TemplateResourceIndexInMemory;

@OpenAPIDefinition(
		info = @Info(
				title = "Provenance API",
				version = "0.0",
				description = "An API to process provenance",
				license = @License(name = "MIT License for ProvToolbox",
						           url = "https://github.com/lucmoreau/ProvToolbox/blob/master/license.txt"),
				contact = @Contact(url = "https://openprovenance.org/",
						           name = "Luc Moreau",
						           email = "provenance@kcl.ac.uk")
		),
		tags = {
				@Tag(name = "documents",  description = "provenance api (documents)",     externalDocs = @ExternalDocumentation(description = "docs desc")),
			//	@Tag(name = "provapi",    description = "provenance api",                 externalDocs = @ExternalDocumentation(description = "docs desc")),
				@Tag(name = "vis",        description = "provenance api (visualisation)", externalDocs = @ExternalDocumentation(description = "docs desc")),
				@Tag(name = "random",     description = "provenance api (random)",        externalDocs = @ExternalDocumentation(description = "docs desc")),
				@Tag(name = "view",       description = "browsing interface",             externalDocs = @ExternalDocumentation(description = "NOTE: /provapi is incorrect and should be /view"))
		},
		externalDocs = @ExternalDocumentation(description = "definition docs desc"),
		security = {
				@SecurityRequirement(name = "req 1", scopes = {"a", "b"}),
				@SecurityRequirement(name = "req 2", scopes = {"b", "c"})
		},
		servers = {
				@Server(
						description = "production",
						url = "https://openprovenance.org/services/"
				),
				@Server(
						description = "dev",
						url = "http://localhost:{port}/",
						variables = {
								@ServerVariable(name = "port", description = "service port", defaultValue = "7070", allowableValues = {"7070", "8080"})
						})
		}
)

@ApplicationPath("/provapi")
public class ProvapiApplication extends Application {
	private Set<Object> singletons = new HashSet<Object>();



	ServiceUtilsConfig config=new ServiceUtilsConfig();

	public ProvapiApplication() {
		initRedis();
		config.documentCacheSize=200;

		PostService ps=new PostService(config);
		singletons.add(ps);
		singletons.add(new TranslationService(ps));
		singletons.add(new TemplateService(ps));
		singletons.add(new VisService(ps));
		//singletons.add(new VisService());
		singletons.add(new OpenApiResource());
		singletons.add(new AcceptHeaderOpenApiResource());
		
		//singletons.add(new io.swagger.jaxrs.listing.SwaggerSerializers());
		//singletons.add(new io.swagger.jaxrs.listing.ApiListingResource());
		singletons.add(new DocumentMessageBodyWriter(new InteropFramework()));
		singletons.add(new NodeMessageBodyWriter());			
		
	    CorsFilter corsFilter = new CorsFilter();
        corsFilter.getAllowedOrigins().add("*");
        corsFilter.setAllowedMethods("OPTIONS, GET, POST, DELETE, PUT, PATCH");
        singletons.add(corsFilter);
        


	}

	public void initInMemory() {
		Consumer<Map<String, Instantiable<?>>> inMemoryInit = extensionMap -> {
			extensionMap.put(DocumentResource.getResourceKind(), DocumentResourceIndexInMemory.factory);
			extensionMap.put(TemplateResource.getResourceKind(), TemplateResourceIndexInMemory.factory);
		};
		Consumer<Map<String, ResourceIndex<?>>> inMemoryInit2 = extensionMap -> {
			DocumentResourceIndexInMemory di=new DocumentResourceIndexInMemory();
			extensionMap.put(DocumentResource.getResourceKind(), di);
			extensionMap.put(TemplateResource.getResourceKind(), new TemplateResourceIndexInMemory(di,TemplateResourceIndexInMemory.factory));
		};

		inMemoryInit.accept(config.extensionMap);
		inMemoryInit2.accept(config.extensionMap2);
		config.documentResourceIndex=new DocumentResourceIndexInMemory();
	}


	public void initRedis() {

		Consumer<Map<String, Instantiable<?>>> redisInit = extensionMap -> {
			extensionMap.put(DocumentResource.getResourceKind(), RedisDocumentResourceIndex.factory);
			extensionMap.put(TemplateResource.getResourceKind(), RedisTemplateResourceIndex.factory);
		};
		Consumer<Map<String, ResourceIndex<?>>> redisInit2 = extensionMap -> {
			RedisDocumentResourceIndex di=new RedisDocumentResourceIndex();
			extensionMap.put(DocumentResource.getResourceKind(), di);
			extensionMap.put(TemplateResource.getResourceKind(), new RedisTemplateResourceIndex(di,RedisTemplateResourceIndex.factory));
		};
		redisInit2.accept(config.extensionMap2);
		redisInit.accept(config.extensionMap);
		config.documentResourceIndex=new RedisDocumentResourceIndex();
	}


	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}

	
}