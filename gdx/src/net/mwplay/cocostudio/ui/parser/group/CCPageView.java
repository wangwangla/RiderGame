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
import net.mwplay.cocostudio.ui.BaseCocoStudioUIEditor;
import net.mwplay.cocostudio.ui.model.objectdata.group.PageViewObjectData;
import net.mwplay.cocostudio.ui.parser.GroupParser;
import net.mwplay.cocostudio.ui.widget.PageView;

public class CCPageView extends GroupParser<PageViewObjectData> {
    @Override
    public String getClassName() {
        return "PageViewObjectData";
    }

    @Override
    public Actor parse(BaseCocoStudioUIEditor editor, PageViewObjectData widget) {
        PageView pageView = new PageView();
        pageView.initGestureListener();
        return pageView;
    }
}
