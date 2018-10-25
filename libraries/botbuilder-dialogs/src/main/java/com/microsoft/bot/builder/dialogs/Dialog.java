package com.microsoft.bot.builder.dialogs;

// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.


/** 
 Base class for all dialogs.
*/
public abstract class Dialog
{
	public static final DialogTurnResult EndOfTurn = new DialogTurnResult(DialogTurnStatus.Waiting);

	public Dialog(String dialogId)
	{
		if (tangible.StringHelper.isNullOrWhiteSpace(dialogId))
		{
			throw new NullPointerException("dialogId");
		}

		Id = dialogId;
	}

	private String Id;
	public final String getId()
	{
		return Id;
	}

	/** 
	 Method called when a new dialog has been pushed onto the stack and is being activated.
	 
	 @param dc The dialog context for the current turn of conversation.
	 @param options (Optional) arguments that were passed to the dialog during `begin()` call that started the instance.
	 @param cancellationToken The cancellation token.
	 @return A <see cref="Task"/> representing the asynchronous operation.
	*/

	public final abstract CompletableFuture<DialogTurnResult> BeginDialogAsync(DialogContext dc, Object options);
	public final abstract CompletableFuture<DialogTurnResult> BeginDialogAsync(DialogContext dc);
//C# TO JAVA CONVERTER NOTE: Java does not support optional parameters. Overloaded method(s) are created above:
//ORIGINAL LINE: public abstract CompletableFuture<DialogTurnResult> BeginDialogAsync(DialogContext dc, object options = null, CancellationToken cancellationToken = default(CancellationToken));
	public abstract CompletableFuture<DialogTurnResult> BeginDialogAsync(DialogContext dc, Object options );

	/** 
	 Method called when an instance of the dialog is the "current" dialog and the
	 user replies with a new activity. The dialog will generally continue to receive the users
	 replies until it calls either `DialogSet.end()` or `DialogSet.begin()`.
	 If this method is NOT implemented then the dialog will automatically be ended when the user replies.
	 
	 @param dc The dialog context for the current turn of conversation.
	 @param cancellationToken The cancellation token.
	 @return A <see cref="Task"/> representing the asynchronous operation.
	*/

	public CompletableFuture<DialogTurnResult> ContinueDialogAsync(DialogContext dc)
	{
		return ContinueDialogAsync(dc, null);
	}

//C# TO JAVA CONVERTER TODO TASK: There is no equivalent in Java to the 'async' keyword:
//ORIGINAL LINE: public virtual async CompletableFuture<DialogTurnResult> ContinueDialogAsync(DialogContext dc, CancellationToken cancellationToken = default(CancellationToken))
//C# TO JAVA CONVERTER NOTE: Java does not support optional parameters. Overloaded method(s) are created above:
	public CompletableFuture<DialogTurnResult> ContinueDialogAsync(DialogContext dc )
	{
		// By default just end the current dialog.
//C# TO JAVA CONVERTER TODO TASK: There is no equivalent to 'await' in Java:
		return await dc.EndDialogAsync(cancellationToken).get();
	}

	/** 
	 Method called when an instance of the dialog is being returned to from another
	 dialog that was started by the current instance using `DialogSet.begin()`.
	 If this method is NOT implemented then the dialog will be automatically ended with a call
	 to `DialogSet.endDialogWithResult()`. Any result passed from the called dialog will be passed
	 to the current dialogs parent.
	 
	 @param dc The dialog context for the current turn of conversation.
	 @param reason Reason why the dialog resumed.
	 @param result (Optional) value returned from the dialog that was called. The type of the value returned is dependant on the dialog that was called.
	 @param cancellationToken The cancellation token.
	 @return A <see cref="Task"/> representing the asynchronous operation.
	*/

	public CompletableFuture<DialogTurnResult> ResumeDialogAsync(DialogContext dc, DialogReason reason, Object result)
	{
		return ResumeDialogAsync(dc, reason, result, null);
	}

	public CompletableFuture<DialogTurnResult> ResumeDialogAsync(DialogContext dc, DialogReason reason)
	{
		return ResumeDialogAsync(dc, reason, null, null);
	}

//C# TO JAVA CONVERTER TODO TASK: There is no equivalent in Java to the 'async' keyword:
//ORIGINAL LINE: public virtual async CompletableFuture<DialogTurnResult> ResumeDialogAsync(DialogContext dc, DialogReason reason, object result = null, CancellationToken cancellationToken = default(CancellationToken))
//C# TO JAVA CONVERTER NOTE: Java does not support optional parameters. Overloaded method(s) are created above:
	public CompletableFuture<DialogTurnResult> ResumeDialogAsync(DialogContext dc, DialogReason reason, Object result )
	{
		// By default just end the current dialog and return result to parent.
//C# TO JAVA CONVERTER TODO TASK: There is no equivalent to 'await' in Java:
		return await dc.EndDialogAsync(result).get();
	}


	public CompletableFuture RepromptDialogAsync(TurnContext turnContext, DialogInstance instance)
	{
		return RepromptDialogAsync(turnContext, instance, null);
	}

//C# TO JAVA CONVERTER NOTE: Java does not support optional parameters. Overloaded method(s) are created above:
//ORIGINAL LINE: public virtual CompletableFuture RepromptDialogAsync(TurnContext turnContext, DialogInstance instance, CancellationToken cancellationToken = default(CancellationToken))
	public CompletableFuture RepromptDialogAsync(TurnContext turnContext, DialogInstance instance )
	{
		// No-op by default
		return Task.CompletedTask;
	}


	public CompletableFuture EndDialogAsync(TurnContext turnContext, DialogInstance instance, DialogReason reason)
	{
		return EndDialogAsync(turnContext, instance, reason, null);
	}

//C# TO JAVA CONVERTER NOTE: Java does not support optional parameters. Overloaded method(s) are created above:
//ORIGINAL LINE: public virtual CompletableFuture EndDialogAsync(TurnContext turnContext, DialogInstance instance, DialogReason reason, CancellationToken cancellationToken = default(CancellationToken))
	public CompletableFuture EndDialogAsync(TurnContext turnContext, DialogInstance instance, DialogReason reason )
	{
		// No-op by default
		return Task.CompletedTask;
	}
}