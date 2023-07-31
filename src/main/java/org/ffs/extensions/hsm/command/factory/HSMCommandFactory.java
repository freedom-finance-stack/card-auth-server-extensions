package org.ffs.extensions.hsm.command.factory;

import org.ffs.extensions.hsm.command.HSMCommand;
import org.ffs.extensions.hsm.command.enums.HSMCommandType;

public interface HSMCommandFactory {

    HSMCommand getHSMCommand(HSMCommandType hsmCommandType);
}
