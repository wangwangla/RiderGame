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
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

import net.mwplay.cocostudio.ui.BaseCocoStudioUIEditor;
import net.mwplay.cocostudio.ui.model.objectdata.widget.TImageObjectData;
import net.mwplay.cocostudio.ui.parser.WidgetParser;
import net.mwplay.cocostudio.ui.widget.TImage;

public class CCTImageView extends WidgetParser<TImageObjectData> {

    @Override
    public String getClassName() {
        return "TImageObjectData";
    }

    @Override
    public Actor parse(BaseCocoStudioUIEditor editor, TImageObjectData widget) {
        TImage image;
        Drawable tr = editor.findDrawable(widget, widget.FileData);
        if (tr == null) {
            return new TImage();
        }

        image = new TImage(tr);

		String buttonType = widget.ButtonType;
        if (buttonType != null) {
            if (buttonType.equals("ScaleButton")) {
                image.isButton();

            } else if (buttonType.equals("ColorButton")) {
                image.isColorButton();

            } else {
                image.isNoButton();

            }
        }
        return image;
    }

}
