package mx.infotec.dads.kukulkan.shell.generator;
/*
 *  
 * The MIT License (MIT)
 * Copyright (c) 2016 Daniel Cortes Pichardo
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

import static mx.infotec.dads.kukulkan.metamodel.util.Validator.requiredNotEmpty;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;

import mx.infotec.dads.kukulkan.engine.templating.service.TemplateService;
import mx.infotec.dads.kukulkan.metamodel.annotation.GeneratorComponent;
import mx.infotec.dads.kukulkan.metamodel.context.GeneratorContext;
import mx.infotec.dads.kukulkan.metamodel.generator.Generator;
import mx.infotec.dads.kukulkan.metamodel.generator.Layer;
import mx.infotec.dads.kukulkan.metamodel.template.Template;
import mx.infotec.dads.kukulkan.metamodel.template.TemplateType;
import mx.infotec.dads.kukulkan.metamodel.util.FileUtil;
import mx.infotec.dads.kukulkan.shell.template.TemplateFactory;
import mx.infotec.dads.kukulkan.shell.util.Antlr4Util;

/**
 * Generator for Angular 1.5.8, Spring boot and Spring Data
 * 
 * @author Daniel Cortes Pichardo
 *
 */
@GeneratorComponent
public class Antlr4Generator implements Generator {

    /** The template service. */
    @Autowired
    private TemplateService templateService;

    /*
     * (non-Javadoc)
     * 
     * @see mx.infotec.dads.kukulkan.metamodel.generator.Generator#getName()
     */
    @Override
    public String getName() {
        return "antlr4";
    }

    /*
     * (non-Javadoc)
     * 
     * @see mx.infotec.dads.kukulkan.metamodel.generator.Generator#getVersion()
     */
    @Override
    public String getVersion() {
        return "1.0.0";
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * mx.infotec.dads.kukulkan.metamodel.generator.Generator#getDescription()
     */
    @Override
    public String getDescription() {
        return "Angular 1.5.8 and Spring boot application";
    }

    @Override
    public void process(GeneratorContext context) {
        Antlr4Context antlrContext = requiredNotEmpty(context.get(Antlr4Context.class));
        Map<String, Object> model = new HashMap<>();
        model.put("project", requiredNotEmpty(context.get(Antlr4Context.class)));
        for (Template template : Antlr4Util.convertTemplate(TemplateType.ANTLR4,
                TemplateFactory.ANTLR4_TEMPLATE_LIST)) {
            Path toSave = Antlr4Util.createToSavePath(context, template, antlrContext.getOutputDir());
            String content = templateService.fillTemplate(template.getName(), model);
            FileUtil.saveToFile(toSave, content);
        }
    }

    @Override
    public Collection<? extends Layer> getLayers() {
        // this is goint to be deleted
        return null;
    }

}