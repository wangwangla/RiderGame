/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.mwplay.cocostudio.ui.parser.group;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import net.mwplay.cocostudio.ui.BaseCocoStudioUIEditor;
import net.mwplay.cocostudio.ui.model.objectdata.group.SingleNodeObjectData;
import net.mwplay.cocostudio.ui.parser.GroupParser;

/**
 * tip 还未支持单色背景属性, 背景图片在Cocostudio里面并不是铺满, 而是居中
 */
public class CCNode extends GroupParser<SingleNodeObjectData> {

	@Override
	public String getClassName() {
		return "SingleNodeObjectData";
	}

	@Override
	public Actor parse(BaseCocoStudioUIEditor editor, SingleNodeObjectData widget) {
		return new Group();
	}

}
