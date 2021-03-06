/*
 * Copyright (c) 2003, 2019, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

package jdk.javadoc.internal.doclets.toolkit;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

/**
 * The interface for writing property output.
 *
 *  <p><b>This is NOT part of any supported API.
 *  If you write code that depends on this, you do so at your own risk.
 *  This code and its internal interfaces are subject to change or
 *  deletion without notice.</b>
 */

public interface PropertyWriter {

    /**
     * Get the property details tree header.
     *
     * @param typeElement the class being documented
     * @param memberDetailsTree the content tree representing member details
     * @return content tree for the property details header
     */
    public Content getPropertyDetailsTreeHeader(TypeElement typeElement,
            Content memberDetailsTree);

    /**
     * Get the property documentation tree header.
     *
     * @param property the property being documented
     * @param propertyDetailsTree the content tree representing property details
     * @return content tree for the property documentation header
     */
    public Content getPropertyDocTreeHeader(ExecutableElement property,
            Content propertyDetailsTree);

    /**
     * Get the signature for the given property.
     *
     * @param property the property being documented
     * @return content tree for the property signature
     */
    public Content getSignature(ExecutableElement property);

    /**
     * Add the deprecated output for the given property.
     *
     * @param property the property being documented
     * @param propertyDocTree content tree to which the deprecated information will be added
     */
    public void addDeprecated(ExecutableElement property, Content propertyDocTree);

    /**
     * Add the comments for the given property.
     *
     * @param property the property being documented
     * @param propertyDocTree the content tree to which the comments will be added
     */
    public void addComments(ExecutableElement property, Content propertyDocTree);

    /**
     * Add the tags for the given property.
     *
     * @param property the property being documented
     * @param propertyDocTree the content tree to which the tags will be added
     */
    public void addTags(ExecutableElement property, Content propertyDocTree);

    /**
     * Get the property details tree.
     *
     * @param memberDetailsTreeHeader the content tree representing member details header
     * @param memberDetailsTree the content tree representing member details
     * @return content tree for the property details
     */
    public Content getPropertyDetails(Content memberDetailsTreeHeader, Content memberDetailsTree);

    /**
     * Get the property documentation.
     *
     * @param propertyDocTree the content tree representing property documentation
     * @return content tree for the property documentation
     */
    public Content getPropertyDoc(Content propertyDocTree);

    /**
     * Gets the member header tree.
     *
     * @return a content tree for the member header
     */
    public Content getMemberTreeHeader();
}
