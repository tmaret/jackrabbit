/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.jackrabbit.jcr2spi.nodetype;

import org.apache.jackrabbit.spi.QPropertyDefinition;
import org.apache.jackrabbit.spi.QNodeDefinition;
import org.apache.jackrabbit.spi.NodeInfo;
import org.apache.jackrabbit.spi.PropertyInfo;
import org.apache.jackrabbit.jcr2spi.hierarchy.NodeEntry;
import org.apache.jackrabbit.jcr2spi.hierarchy.PropertyEntry;
import org.apache.jackrabbit.jcr2spi.state.NodeState;
import org.apache.jackrabbit.jcr2spi.state.PropertyState;
import org.apache.jackrabbit.name.QName;

import javax.jcr.RepositoryException;
import javax.jcr.nodetype.NoSuchNodeTypeException;
import javax.jcr.nodetype.ConstraintViolationException;

/**
 * <code>ItemDefinitionManager</code>...
 */
public interface ItemDefinitionProvider {

    public QNodeDefinition getRootNodeDefinition();

    public QNodeDefinition getQNodeDefinition(NodeEntry nodeEntry, NodeInfo nodeInfo);

    public QNodeDefinition getQNodeDefinition(NodeState nodeState) throws RepositoryException;

    /**
     * Returns the applicable child node definition for a child node with the
     * specified name and node type.
     *
     * @param parentState
     * @param name
     * @param nodeTypeName
     * @return
     * @throws NoSuchNodeTypeException
     * @throws ConstraintViolationException if no applicable child node definition
     * could be found
     */
    public QNodeDefinition getQNodeDefinition(NodeState parentState, QName name, QName nodeTypeName)
            throws NoSuchNodeTypeException, ConstraintViolationException;

    /**
     * Returns the applicable child node definition for a child node with the
     * specified name and node type.
     *
     * @param ent
     * @param name
     * @param nodeTypeName
     * @return
     * @throws NoSuchNodeTypeException
     * @throws ConstraintViolationException if no applicable child node definition
     * could be found
     */
    public QNodeDefinition getQNodeDefinition(EffectiveNodeType ent, QName name, QName nodeTypeName)
            throws NoSuchNodeTypeException, ConstraintViolationException;

    public QPropertyDefinition getQPropertyDefinition(PropertyEntry propertyEntry, PropertyInfo propInfo);

    public QPropertyDefinition getQPropertyDefinition(PropertyState propertyState) throws RepositoryException;

    /**
     * Returns the applicable property definition for a property with the
     * specified name, type and multiValued characteristic. If there more than
     * one applicable definitions that would apply to the given params a
     * ConstraintViolationException is thrown.
     *
     * @param ntName
     * @param propName
     * @param type
     * @param multiValued
     * @return
     * @throws NoSuchNodeTypeException If no node type with name <code>ntName</code>
     * exists.
     * @throws ConstraintViolationException if no applicable property definition
     *                                      could be found
     */
    public QPropertyDefinition getQPropertyDefinition(QName ntName,
                                                      QName propName, int type,
                                                      boolean multiValued)
            throws ConstraintViolationException, NoSuchNodeTypeException;

    /**
     * Returns the applicable property definition for a property with the
     * specified name, type and multiValued characteristic. If there more than
     * one applicable definitions then the following rules are applied:
     * <ul>
     * <li>named definitions are preferred to residual definitions</li>
     * <li>definitions with specific required type are preferred to definitions
     * with required type UNDEFINED</li>
     * </ul>
     *
     * @param parentState
     * @param name
     * @param type
     * @param multiValued
     * @return
     * @throws ConstraintViolationException if no applicable property definition
     *                                      could be found
     */
    public QPropertyDefinition getQPropertyDefinition(NodeState parentState,
                                                      QName name, int type,
                                                      boolean multiValued)
            throws ConstraintViolationException, NoSuchNodeTypeException;

    /**
     * Returns the applicable property definition for a property with the
     * specified name and type. The multiValued flag is not taken into account
     * in the selection algorithm. Other than
     * <code>{@link #getApplicablePropertyDefinition(QName, int, boolean)}</code>
     * this method does not take the multiValued flag into account in the
     * selection algorithm. If there more than one applicable definitions then
     * the following rules are applied:
     * <ul>
     * <li>named definitions are preferred to residual definitions</li>
     * <li>definitions with specific required type are preferred to definitions
     * with required type UNDEFINED</li>
     * <li>single-value definitions are preferred to multiple-value definitions</li>
     * </ul>
     *
     * @param parentState
     * @param name
     * @param type
     * @return
     * @throws ConstraintViolationException if no applicable property definition
     *                                      could be found
     */
    public QPropertyDefinition getQPropertyDefinition(NodeState parentState, QName name, int type)
            throws ConstraintViolationException, NoSuchNodeTypeException;
}