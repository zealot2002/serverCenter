package com.zzy.processors;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import com.zzy.annotations.ScActionAnnotation;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;

import static javax.lang.model.element.Modifier.PUBLIC;

/**
 *
 */
public class ScActionProcessor extends AbstractProcessor {


    private static final String KEY_MODULE_NAME = "moduleName";
    private Filer mFiler;
    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        mFiler = processingEnv.getFiler();
    }


    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        test1(roundEnv);
        return false;
    }

    private void test(RoundEnvironment roundEnv){
        for (Element element : roundEnv.getElementsAnnotatedWith(ScActionAnnotation.class)) {
            System.out.println("------------------------------");
            if (element.getKind() == ElementKind.CLASS) {
                TypeElement typeElement = (TypeElement) element;
                System.out.println(typeElement.getSimpleName());
                System.out.println(typeElement.getAnnotation(ScActionAnnotation.class).value());
            }
            System.out.println("------------------------------");
        }
    }
    private void test1(RoundEnvironment roundEnv){
        String moduleName = getModuleName(roundEnv);
        String className = getGeneratedClassName(moduleName);
        String packageName = "com.zzy.processor.generated";

        String methodStatement = getMethodStatement(roundEnv);
        generateClassFile(packageName,className,methodStatement);
//        StringBuilder builder = new StringBuilder()
//                .append("package "+packageName+";\n\n")
//                .append("public class "+className+" {\n\n") // open class
//                .append("\tpublic String toString() {\n") // open method
//                .append("\t\treturn \"");
//
//        Map<String ,String> actionMap = new HashMap<>();
//        // for each javax.lang.model.element.Element annotated with the CustomAnnotation
//        for (Element element : roundEnv.getElementsAnnotatedWith(ScActionAnnotation.class)) {
//            TypeElement typeElement = (TypeElement) element;
//            String actionName = typeElement.getAnnotation(ScActionAnnotation.class).value();
//            String clazzName = typeElement.getQualifiedName().toString();
//            actionMap.put(actionName,clazzName);
//        }
//
//        // this is appending to the return statement
//        builder.append(actionMap.toString());
//
//        builder.append("\";\n") // end return
//                .append("\t}\n") // close method
//                .append("}\n"); // close class
//
//        try { // write the file
//
//            JavaFileObject source = processingEnv.getFiler().createSourceFile(packageName+"."+className);
////
////            JavaFile.builder(PACKAGE_OF_GENERATE_FILE,
////                    TypeSpec.classBuilder(providerMapFileName)
////                            .addJavadoc(WARNING_TIPS)
////                            .addSuperinterface(ClassName.get(type_IProviderGroup))
////                            .addModifiers(PUBLIC)
////                            .addMethod(loadIntoMethodOfProviderBuilder.build())
////                            .build()
////            ).build().writeTo(mFiler);
//
//            Writer writer = source.openWriter();
//            writer.write(builder.toString());
//            writer.flush();
//            writer.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//            // Note: calling e.printStackTrace() will print IO errors
//            // that occur from the file already existing after its first run, this is normal
//        }
    }

    private String getMethodStatement(RoundEnvironment roundEnv) {
        StringBuilder builder = new StringBuilder()
                .append("return \"");

        Map<String ,String> actionMap = new HashMap<>();
        // for each javax.lang.model.element.Element annotated with the CustomAnnotation
        for (Element element : roundEnv.getElementsAnnotatedWith(ScActionAnnotation.class)) {
            TypeElement typeElement = (TypeElement) element;
            String actionName = typeElement.getAnnotation(ScActionAnnotation.class).value();
            String clazzName = typeElement.getQualifiedName().toString();
            actionMap.put(actionName,clazzName);
        }

        // this is appending to the return statement
        builder.append(actionMap.toString());

        builder.append("\"");
        return builder.toString();
    }

    private void generateClassFile(String packageName, String fileName, final String methodStatement){
        MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("toString")
                .addModifiers(PUBLIC)
                .addStatement(methodStatement)
                .returns(String.class)
                .addAnnotation(Override.class);

        // Write provider into disk
        try{
            JavaFile.builder(packageName,
                    TypeSpec.classBuilder(fileName)
                            .addJavadoc(Constants.WARNING_TIPS)
                            .addModifiers(PUBLIC)
                            .addMethod(methodBuilder.build())
                            .build()
            ).build().writeTo(mFiler);
        }catch(Exception e){
            //normal case: Attempt to recreate a file for type...
        }

    }
    private String getGeneratedClassName(String moduleName) {
        return moduleName+"GeneratedActionMap";
    }

    private String getModuleName(RoundEnvironment roundEnv){
        // Attempt to get user configuration [moduleName]
        String moduleName = "";
        Map<String, String> options = processingEnv.getOptions();
        if (options!=null
                &&options.containsKey(KEY_MODULE_NAME)
                ) {
            moduleName = options.get(KEY_MODULE_NAME);
        }
        return moduleName;
    }
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotataions = new LinkedHashSet<String>();
        annotataions.add(ScActionAnnotation.class.getCanonicalName());
        return annotataions;
    }


    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }
}
