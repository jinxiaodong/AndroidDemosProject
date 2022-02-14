// IMyAidlInterface.aidl
package com.jarvis.aidlclent;

// Declare any non-default types here with import statements

interface IMyAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
       void client2server(in ParcelFileDescriptor pfd);

}