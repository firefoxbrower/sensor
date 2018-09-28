package testBeetl;

import java.io.File;
import java.io.IOException;

import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.misc.BeetlUtil;
import org.beetl.core.resource.ClasspathResourceLoader;
import org.beetl.core.resource.FileResourceLoader;
import org.beetl.core.resource.StringTemplateResourceLoader;
import org.beetl.core.resource.WebAppResourceLoader;

public class Test {

	@org.junit.Test
	public void test() throws IOException{
		
		// 1.字符串模板加载器
		//GroupTemplate需要俩个参数，一个是模板资源加载器，一个是配置类
		StringTemplateResourceLoader resourceLoader = new StringTemplateResourceLoader();
		Configuration cfg = Configuration.defaultConfiguration();
		GroupTemplate gt = new GroupTemplate(resourceLoader, cfg);
		Template t = gt.getTemplate("hello,${name}");
		//将变量name传入模板里，其值是"Beetl"
		t.binding("name", "beetl");
		//渲染模板
		String str = t.render(); //返回渲染结果
		//template.renderTo(Writer) 渲染结果输出到Writer里
		//template.renderTo(OutputStream ) 渲染结果输出到OutputStream里
		System.out.println("字符串模板加载器"+str);
		
		
	}
	
	@org.junit.Test
	public void test4() throws IOException{
		//4.WebApp资源模板加载器 WebAppResourceLoader 是用于web应用的资源模板加载器，默认根路径是WebRoot目录。
		//也可以通过制定root属性来设置相对于WebRoot的的模板根路径，从安全角考虑，建议放到WEB-INF目录下 如下是Jfinal集成 里初始化GroupTemplate的方法
		// 指定了模板根目录，即搜索模板的时候从根目录开始，
		//如果new ClasspathResourceLoader("template/"),则表示搜索template下的模板。此处用空构造函数，表示搜索路径是根路径，且字符集默认字符集UTF-8.
		Configuration cfg = Configuration.defaultConfiguration();
		WebAppResourceLoader resourceLoader = new WebAppResourceLoader();
		System.out.println(BeetlUtil.getWebRoot() + File.separator);
		//resourceLoader.setRoot(BeetlUtil.getWebRoot() + File.separator+"/src/main/webapp/WEB-INF/template/s01");
		GroupTemplate groupTemplate = new GroupTemplate(resourceLoader, cfg);
		Template t = groupTemplate.getTemplate("/hello.txt");
		String str = t.render();
		System.out.println(str);
		//WebAppResourceLoader 假定 beetl.jar 是位于 WEB-INF/lib 目录下，
		//因此，可以通过WebAppResourceLoader类的路径来推断出WebRoot路径从而指定模板根路径。所有线上环境一般都是如此，
		//如果是开发环境或者其他环境不符合此假设，你需要调用resourceLoader.setRoot() 来指定模板更路径
	}
	
/*	@org.junit.Test
	public void test2() throws IOException{
		
		// 2. 文件资源模板加载器
		 //指定了模板根目录，即位于项目工程下的template目录
				String root = System.getProperty("user.dir")+File.separator+"template";
			//	构造了一个资源加载器，并指定字符集为UTF-8 (也可不指定，因为配置文件默认就是UTF-8)
				FileResourceLoader resourceLoader = new FileResourceLoader(root,"utf-8");
				Configuration cfg = Configuration.defaultConfiguration();
				GroupTemplate gt = new GroupTemplate(resourceLoader, cfg);
			//	通过模板的相对路径/s01/hello.txt来加载模板
				Template t = gt.getTemplate("/s01/hello.txt");
				String str = t.render();
				System.out.println(str);
		
	}*/
	
	/*@org.junit.Test
	public void test3() throws IOException{
		//3.Classpath资源模板加载器 模板资源是打包到jar文件或者同Class放在一起，因此，可以使用ClasspathResourceLoader来加载模板实例
		// 指定了模板根目录，即搜索模板的时候从根目录开始，
		//如果new ClasspathResourceLoader("template/"),则表示搜索template下的模板。此处用空构造函数，表示搜索路径是根路径，且字符集默认字符集UTF-8.
		ClasspathResourceLoader resourceLoader = new ClasspathResourceLoader("com/sensor/beetl/sample/s01/");
		Configuration cfg = Configuration.defaultConfiguration();
		GroupTemplate gt = new GroupTemplate(resourceLoader, cfg);
		//通过模板的相对路径org/beetl/sample/s01/hello.txt来加载模板
		Template t = gt.getTemplate("/hello.txt");
		String str = t.render();
		System.out.println(str);
		
	}
	
	@org.junit.Test
	public void test4() throws IOException{
		//4.WebApp资源模板加载器 WebAppResourceLoader 是用于web应用的资源模板加载器，默认根路径是WebRoot目录。
		//也可以通过制定root属性来设置相对于WebRoot的的模板根路径，从安全角考虑，建议放到WEB-INF目录下 如下是Jfinal集成 里初始化GroupTemplate的方法
		// 指定了模板根目录，即搜索模板的时候从根目录开始，
		//如果new ClasspathResourceLoader("template/"),则表示搜索template下的模板。此处用空构造函数，表示搜索路径是根路径，且字符集默认字符集UTF-8.
		Configuration cfg = Configuration.defaultConfiguration();
		WebAppResourceLoader resourceLoader = new WebAppResourceLoader();
		
		resourceLoader.setRoot(BeetlUtil.getWebRoot() + File.separator+"/src/main/webapp/WEB-INF/template/s01");
		GroupTemplate groupTemplate = new GroupTemplate(resourceLoader, cfg);
		Template t = groupTemplate.getTemplate("/hello.txt");
		String str = t.render();
		System.out.println(str);
		//WebAppResourceLoader 假定 beetl.jar 是位于 WEB-INF/lib 目录下，
		//因此，可以通过WebAppResourceLoader类的路径来推断出WebRoot路径从而指定模板根路径。所有线上环境一般都是如此，
		//如果是开发环境或者其他环境不符合此假设，你需要调用resourceLoader.setRoot() 来指定模板更路径
	}*/
	
}
