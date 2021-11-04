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

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import net.mwplay.cocostudio.ui.BaseCocoStudioUIEditor;
import net.mwplay.cocostudio.ui.model.objectdata.widget.TextAtlasObjectData;
import net.mwplay.cocostudio.ui.parser.WidgetParser;
import net.mwplay.cocostudio.ui.widget.LabelAtlas;

public class CCTextAtlas extends WidgetParser<TextAtlasObjectData> {
	@Override
	public String getClassName () {
		return "TextAtlasObjectData";
	}

	@Override
	public Actor parse (BaseCocoStudioUIEditor editor, TextAtlasObjectData widget) {
		TextureRegion textureRegion = editor.findTextureRegion(widget, widget.LabelAtlasFileImage_CNB);
		return new LabelAtlas(textureRegion, widget.CharWidth, widget.CharHeight, "./0123456789", widget.LabelText);
	}
}
