package com.github.uiautomator.stub;

public class SimcardInfo {

    int _simID;
    int _slotID;


    public SimcardInfo(int simID, int slotID) {
        this._simID = simID;
        this._slotID = slotID;
    }


    public int getSimID() {
        return _simID;
    }

    public void setSimID(int simID) {
        this._simID = simID;
    }

    public int getSlotID() {
        return _slotID;
    }

    public void setSlotID(int slotID) {
        this._slotID = slotID;
    }
}
