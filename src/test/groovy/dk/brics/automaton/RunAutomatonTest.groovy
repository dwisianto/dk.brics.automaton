package dk.brics.automaton

import spock.lang.Specification

class RunAutomatonTest extends Specification {
    def automatonProvider = new DatatypesAutomatonProvider()

    def "simple"() {
        expect:
        def regExp = new RegExp(rx)
        def automaton = regExp.toAutomaton()
        def runAutomaton = new RunAutomaton(automaton)
        runAutomaton.run(input) == match

        where:
        rx       | input  || match
        "abc"    | "abc"  || true
        "a*"     | ""     || true
        "a*"     | "a"    || true
        "a*"     | "aa"   || true
        "a*"     | "aaa"  || true
        "a*"     | "baaa" || false

        "\u16D4" | "ᛔ"    || true
    }

    def "unicode"() {

    }

    def "complex"() {
        expect:
        def regExp = new RegExp(rx, RegExp.AUTOMATON)
        def automaton = regExp.toAutomaton(automatonProvider)
        def runAutomaton = new RunAutomaton(automaton)
        runAutomaton.run(input) == match

        where:
        rx      | input               || match
        "<URI>" | "http://google.com" || true
    }

    def "long expression"() {
        setup:
        def regExp = new RegExp("(spinat OR chili OR iceberg OR salat OR rosenkål OR skalotteløg OR peberrod OR 'bagekartof.*' OR savoykål OR 'spirer.*' OR snitløg OR 'salat baby' OR 'krydderurt.*' OR 'cherrytomat.*' OR 'romanesco.*' OR 'rodfrugt.*' OR 'lollo rossa' OR rucola OR rucula OR gulerodsmix OR ærter OR majs OR 'buketgrønt.*' OR 'lollo bionda' OR peber AND 'bl.*' OR gul AND peber OR 'avocado.*' OR 'avokado.*' OR 'lammefjord.*' OR rød AND peber OR græskar OR 'agurk.*' OR broccoli OR rødkål OR hvidkål OR 'porre.*' OR blomkål OR 'rødbede.*' OR iceberg OR 'champignon.*' OR salat AND krøl OR 'gulrerod.*' OR gulerødder OR kartofler OR selleri OR karse OR squash OR 'radise.*' OR asparges OR rødløg OR løg OR pastinak OR spidskål OR bladselleri OR fennikel OR kinakål OR tomater) NOT (pesto OR kims OR lindt OR 'nugget.*' OR heinz OR tempt OR stødt OR cacao OR carne OR fisk OR 'pean.*' OR sauce OR pulver OR nudler OR nødder OR gouda OR cheddar OR rygeost OR laks OR pasta OR oliven OR 'kyll.*' OR 'mayo.*' OR skaldyr OR sommer OR ægge OR tun OR bähncke OR tang OR 'sild.*' OR reje OR russisk OR ost OR karry OR 'høns.*' OR skinke OR makrel OR bæhncke OR coleslaw OR k OR tern OR 'ita.*' OR 'x-tra' OR 'håndværk.*' OR 'sylte.*' OR 'puré.*' OR beauvais OR 'agurke.*' OR 'kogt.*' OR buko OR 'chutney.*' OR 'riste.*' OR kohberg OR 'brød.*' OR 'stykke.*' OR 'bolle.*' OR 'chips.*' OR tuborg OR 'kerne.*' OR 'kart.*' AND salat OR knorr OR harboe OR 'suppe.*' OR 'tærte.*' OR oscar OR 'souffle.*' OR 'soufflé.*' OR 'fyld.*' OR 'gratin.*' OR 'farsere.*' OR 'fløde.*' OR 'omelet.*' OR steg OR stegte)")

        when:
        def automaton = regExp.toAutomaton()
        def runAutomaton = new RunAutomaton(automaton)
        def string = runAutomaton.toSerializedString()

        then:
        true
    }
}
