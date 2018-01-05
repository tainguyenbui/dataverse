package edu.harvard.iq.dataverse.engine.command.impl;

import edu.harvard.iq.dataverse.DataFile;
import edu.harvard.iq.dataverse.FileMetadata;
import edu.harvard.iq.dataverse.authorization.Permission;
import edu.harvard.iq.dataverse.engine.command.AbstractCommand;
import edu.harvard.iq.dataverse.engine.command.CommandContext;
import edu.harvard.iq.dataverse.engine.command.DataverseRequest;
import edu.harvard.iq.dataverse.engine.command.RequiredPermissions;
import edu.harvard.iq.dataverse.engine.command.exception.CommandException;
import java.util.logging.Logger;

@RequiredPermissions(Permission.EditDataset)
public class UpdateProvFreeFormCommand extends AbstractCommand<DataFile> {

    private static final Logger logger = Logger.getLogger(UpdateProvFreeFormCommand.class.getCanonicalName());

    private final DataFile dataFile;
    private final String userInput;

    public UpdateProvFreeFormCommand(DataverseRequest aRequest, DataFile dataFile, String userInput) {
        super(aRequest, dataFile);
        this.dataFile = dataFile;
        this.userInput = userInput;
    }

    //MAD: Temporary code from persist (create).
    @Override
    public DataFile execute(CommandContext ctxt) throws CommandException {
        ctxt.engine().submit(new DeleteProvFreeFormCommand(getRequest(), dataFile));
        return ctxt.engine().submit(new PersistProvFreeFormCommand(getRequest(), dataFile, userInput));
    }

}
