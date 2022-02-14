// ICallbackInterface.aidl
package com.jarvis.aidlserver;

// Declare any non-default types here with import statements

interface ICallbackInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
       void server2client(in ParcelFileDescriptor pfd);

}