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
package mx.infotec.dads.kukulkan.shell.config;

import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import mx.infotec.dads.kukulkan.shell.domain.NativeCommand;
import mx.infotec.dads.kukulkan.shell.domain.NativeCommandContext;
import mx.infotec.dads.kukulkan.shell.services.NativeCommandProvided;
import mx.infotec.dads.kukulkan.shell.services.NativeCommandService;

/**
 * The Class NativeCommandContextConfiguration.
 */
@Configuration
public class NativeCommandContextConfiguration {

    /**
     * Config native command context.
     *
     * @param nativeCommands the native commands
     * @param commandService the command service
     * @return the native command context
     */
    @Bean
    public NativeCommandContext configNativeCommandContext(List<NativeCommandProvided> nativeCommands,
            NativeCommandService commandService) {
        NativeCommandContext context = new NativeCommandContext();
        context.setAvailableCommands(mapDefaultNativeCommands(nativeCommands, commandService));
        return context;
    }

    /**
     * Map default native commands.
     *
     * @param nativeCommands the native commands
     * @param commandService the command service
     * @return the sorted map
     */
    private SortedMap<String, NativeCommand> mapDefaultNativeCommands(List<NativeCommandProvided> nativeCommands,
            NativeCommandService commandService) {
        SortedMap<String, NativeCommand> cmdSet = new TreeMap<>();
        nativeCommands.forEach(opt -> opt.getNativeCommand().ifPresent(cmd -> {
            commandService.isPresent(cmd);
            cmdSet.put(cmd.getCommand(), cmd);
        }));
        return cmdSet;
    }
}
