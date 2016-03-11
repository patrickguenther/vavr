/*     / \____  _    _  ____   ______  / \ ____  __    _______
 *    /  /    \/ \  / \/    \ /  /\__\/  //    \/  \  //  /\__\   JΛVΛSLΛNG
 *  _/  /  /\  \  \/  /  /\  \\__\\  \  //  /\  \ /\\/ \ /__\ \   Copyright 2014-2016 Javaslang, http://javaslang.io
 * /___/\_/  \_/\____/\_/  \_/\__\/__/\__\_/  \_//  \__/\_____/   Licensed under the Apache License, Version 2.0
 */
package javaslang.match.model;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.util.Elements;
import java.lang.annotation.Annotation;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Representation of a method.
 *
 * @author Daniel Dietrich
 * @since 2.0.0
 */
public class MethodModel {

    private final Elements elementUtils;
    private final ExecutableElement executableElement;

    public MethodModel(Elements elementUtils, ExecutableElement executableElement) {
        this.elementUtils = elementUtils;
        this.executableElement = executableElement;
    }

    public ExecutableElement getExecutableElement() {
        return executableElement;
    }

    public String getName() {
        return executableElement.getSimpleName().toString();
    }

    public ParameterModel getParameter(int index) {
        final List<? extends VariableElement> parameters = executableElement.getParameters();
        if (index < 0 || index > parameters.size()) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        return new ParameterModel(elementUtils, parameters.get(index));
    }

    public ClassModel getReturnType() {
        return new ClassModel(elementUtils, (DeclaredType) executableElement.getReturnType());
    }

    public List<TypeParameterModel> getTypeParameters() {
        return executableElement.getTypeParameters().stream()
                .map(typeParam -> new TypeParameterModel(elementUtils, typeParam.asType()))
                .collect(toList());
    }

    public <A extends Annotation> boolean isAnnotatedWith(Class<A> annotationType) {
        return executableElement.getAnnotationsByType(annotationType).length > 0;
    }

    @Override
    public String toString() {
        return executableElement.toString();
    }
}
