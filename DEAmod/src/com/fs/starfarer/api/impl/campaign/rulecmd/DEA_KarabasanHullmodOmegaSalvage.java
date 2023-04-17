package com.fs.starfarer.api.impl.campaign.rulecmd;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.*;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.rules.CommandPlugin;
import com.fs.starfarer.api.campaign.rules.MemoryAPI;
import com.fs.starfarer.api.impl.campaign.rulecmd.BaseCommandPlugin;
import com.fs.starfarer.api.util.Misc;
import data.hullmods.Karabasan.DEA_KarabasanAMSRMModification;
import data.hullmods.Karabasan.DEA_KarabasanResonatorModification;
import data.hullmods.Karabasan.DEA_KarabasanRiftModification;

import java.util.List;
import java.util.Map;

public class DEA_KarabasanHullmodOmegaSalvage implements CommandPlugin {
    protected CampaignFleetAPI fleet;
    protected SectorEntityToken entity;
    protected FactionAPI playerFaction;
    protected FactionAPI entityFaction;
    protected TextPanelAPI text;
    protected OptionPanelAPI options;
    protected CargoAPI cargo;
    protected MemoryAPI memory;
    protected MarketAPI market;
    protected InteractionDialogAPI dialog;
    protected Map<String, MemoryAPI> memoryMap;
    protected FactionAPI faction;

    public void DEA_AddKarabasanHullmods() {
    }

    public DEA_KarabasanHullmodOmegaSalvage(SectorEntityToken entity) {
        init(entity);
    }

    protected void init(SectorEntityToken entity) {
        memory = entity.getMemoryWithoutUpdate();
        this.entity = entity;
        fleet = Global.getSector().getPlayerFleet();
        cargo = fleet.getCargo();
        playerFaction = Global.getSector().getPlayerFaction();
        entityFaction = entity.getFaction();
        faction = entity.getFaction();
        market = entity.getMarket();
    }

    @Override
    public boolean execute(String ruleid, InteractionDialogAPI dialog, List<Misc.Token> params, Map<String, MemoryAPI> memoryMap) {
        this.dialog = dialog;
        this.memoryMap = memoryMap;

        String command = params.get(0).getString(memoryMap);
        if (command == null) return false;

        entity = dialog.getInteractionTarget();
        init(entity);

        text = dialog.getTextPanel();
        options = dialog.getOptionPanel();

        if (command.equals("DEA_Add_KarabasanHullmodOmegaSalvage")) {
            CargoAPI cargo = Global.getSector().getPlayerFleet().getCargo();
            cargo.addHullmods("DEA_KarabasanAMSRMModification", 1);
            cargo.addHullmods("DEA_KarabasanResonatorModification", 1);
            cargo.addHullmods("DEA_KarabasanRiftModification", 1);
        }

        return true;
    }

    @Override
    public boolean doesCommandAddOptions() {
        return false;
    }

    @Override
    public int getOptionOrder(List<Misc.Token> params, Map<String, MemoryAPI> memoryMap) {
        return 0;
    }


}
