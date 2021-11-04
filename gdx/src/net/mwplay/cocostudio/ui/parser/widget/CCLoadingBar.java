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
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import net.mwplay.cocostudio.ui.BaseCocoStudioUIEditor;
import net.mwplay.cocostudio.ui.model.objectdata.widget.LoadingBarObjectData;
import net.mwplay.cocostudio.ui.parser.WidgetParser;
import net.mwplay.cocostudio.ui.widget.TLoadingBar;

public class CCLoadingBar extends WidgetParser<LoadingBarObjectData> {

	@Override
	public String getClassName () {
		return "LoadingBarObjectData";
	}

	@Override
	public Actor parse (BaseCocoStudioUIEditor editor, LoadingBarObjectData widget) {
		if (widget.ImageFileData == null) {
			return new Image();
		}

		TextureRegion textureRegion = editor.findTextureRegion(widget, widget.ImageFileData);
		if (textureRegion == null) {
			return new Image();
		}
		TLoadingBar loadingBar = new TLoadingBar(textureRegion);
		loadingBar.setValue(widget.ProgressInfo);

		return loadingBar;
	}

}
