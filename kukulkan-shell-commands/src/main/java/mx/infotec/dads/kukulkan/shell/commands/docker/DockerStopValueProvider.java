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
package mx.infotec.dads.kukulkan.shell.commands.docker;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.shell.CompletionContext;
import org.springframework.shell.CompletionProposal;
import org.springframework.shell.standard.ValueProviderSupport;
import org.springframework.stereotype.Component;

import mx.infotec.dads.kukulkan.shell.domain.Line;
import mx.infotec.dads.kukulkan.shell.domain.ShellCommand;
import mx.infotec.dads.kukulkan.shell.domain.ShellCompletionProposal;
import mx.infotec.dads.kukulkan.shell.services.CommandService;
import mx.infotec.dads.kukulkan.shell.util.LineValuedProcessor;

import static mx.infotec.dads.kukulkan.shell.commands.docker.DockerCommands.DOCKER_COMMAND;

/**
 * The Class DockerStopValueProvider.
 *
 * @author Daniel Cortes Pichardo
 */
@Component
public class DockerStopValueProvider extends ValueProviderSupport {

    /** The command service. */
    @Autowired
    CommandService commandService;

    /* (non-Javadoc)
     * @see org.springframework.shell.standard.ValueProvider#complete(org.springframework.core.MethodParameter, org.springframework.shell.CompletionContext, java.lang.String[])
     */
    @Override
    public List<CompletionProposal> complete(MethodParameter parameter, CompletionContext completionContext,
            String[] hints) {
        LineValuedProcessor processor = (line -> {
            String[] split = line.split("\\s{2,}");
            if (split.length > 0 && !split[0].equals("CONTAINER ID")) {
                return Optional.ofNullable(new Line(split[0], split[6]));
            } else {
                return Optional.empty();
            }
        });
        List<Line> lines = commandService.exec(new ShellCommand(DOCKER_COMMAND).addArg("ps"), processor);
        return lines.stream().map(line -> {
            return new ShellCompletionProposal(line.getText(), line.getDescription());
        }).collect(Collectors.toList());
    }
}