package com.navneet.mcp_client.service;

public interface TimeService {

    /**
     * Get the current time in milliseconds.
     * Tool name: getCurrentTimeInMillis
     * Tool description: Returns the current system time represented as milliseconds since epoch
     */
    Long currentTimeInMillis();

    /**
     * Get the current time in seconds.
     * Tool name: getCurrentTimeInSeconds
     * Tool description: Returns the current system time represented as seconds since epoch
     */
    Long currentTimeInSeconds();

    /**
     * Get the current date as a string.
     * Tool name: getCurrentDate
     * Tool description: Returns the current date in a formatted string representation
     */
    String currentDate();

    /**
     * Get the current date and time as a string.
     * Tool name: getCurrentDateAndTime
     * Tool description: Returns the current date and time in a formatted string representation
     */
    String currentDateAndTime();

    /**
     * Convert a date string to milliseconds.
     * Tool name: convertDateToMillis
     * Tool description: Converts a provided date string into milliseconds since epoch
     */
    Long dateToMillis(String dateStr);

    /**
     * Convert a date string to seconds.
     * Tool name: convertDateToSeconds
     * Tool description: Converts a provided date string into seconds since epoch
     */
    Long dateToSeconds(String dateStr);

    /**
     * Convert milliseconds to a date string.
     * Tool name: convertMillisToDate
     * Tool description: Converts milliseconds since epoch into a formatted date string
     */
    String millisToDate(Long millis);

    /**
     * Convert seconds to a date string.
     * Tool name: convertSecondsToDate
     * Tool description: Converts seconds since epoch into a formatted date string
     */
    String secondsToDate(Long seconds);
}
