package me.linxx.buyheart.main;


import me.linxx.util.file.FileManager;


public enum Messages {

	ON_BUY_HEART( "on_buy_heart" ), NO_PERMISSION_MESSAGE( "no_permission_message" ), NOT_ENOUGHT_MONEY_MESSAGE(
			"not_enought_money_message" ), ON_SETHEARTS( "on_sethearts" ), TARGET_IS_NO_PLAYER( "target_is_no_player" );

	public FileManager fm = new FileManager( "Messages.yml" );
	public String name;
	public String Message;

	private Messages( String name ) {

		this.Message = fm.getConfig().getString( name );

	}
}