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
package net.mwplay.cocostudio.ui.parser.widget;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import net.mwplay.cocostudio.ui.BaseCocoStudioUIEditor;
import net.mwplay.cocostudio.ui.model.objectdata.widget.SpriteObjectData;
import net.mwplay.cocostudio.ui.parser.WidgetParser;

public class CCSpriteView extends WidgetParser<SpriteObjectData> {

	@Override
	public String getClassName() {
		return "SpriteObjectData";
	}

	@Override
	public Actor parse(BaseCocoStudioUIEditor editor, SpriteObjectData widget) {
		Drawable tr = editor.findDrawable(widget, widget.FileData);
		if (tr == null) {
			return new Image();
		}
		Image image = new Image(tr);

		return image;
	}

	@Override
	public Group widgetChildrenParse(BaseCocoStudioUIEditor editor, SpriteObjectData widget, Group parent, Actor actor) {
		Group group = super.widgetChildrenParse(editor, widget, parent, actor);

		group.setTouchable(Touchable.enabled);

		return group;
	}
}
