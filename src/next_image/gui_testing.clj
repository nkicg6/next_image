;; playing with layout of gui
(ns next-image.gui-testing
  (:require [seesaw.core :as seesaw]
            [seesaw.chooser :as chooser]
            [seesaw.font :as font]))

(defn make-fake-files [prefix n]
  (into [] (for [ x (range n)] (str prefix x))))


(def next-image-state
  "holds all state for app"
  (atom {:todo-files (make-fake-files "f" 5)
         :done-files (make-fake-files "done-" 3)
         :current-selected nil
         :todo-files-count 5
         :done-files-count 3}))

(defn update-selection [selected-file]
  (swap! next-image-state :current-selected selected-file))

(defn make-listbox [model-content id]
  (seesaw/scrollable (seesaw/listbox :model model-content) :id id))

(defn tabbed-top
  [frame]
  (seesaw/config! frame :content
                  
                  (seesaw/tabbed-panel :tabs
                                       [{:title "todo..." :content   (seesaw/scrollable (seesaw/listbox :model (:todo-files @next-image-state)) :id "todos")}

                                        {:title "done" :content (make-listbox (:done-files @next-image-state) "dones")}])))



(def test-frame
  (seesaw/frame :title "base frame" :width 400 :height 400
                :content (seesaw/label :text "testing")))
(-> test-frame seesaw/show!)

(tabbed-top test-frame)


;; this is how you add listeners based on a widget's :id

#_(seesaw/listen
 (seesaw/select test-frame [:#todos])
 :selection (fn [e] (swap! next-image-state assoc :current-selected (seesaw/selection e))))

(seesaw/listen
 (seesaw/select test-frame [:#todos])
 :selection (fn [e] (println "pushed")))

#_(def test-scroll (seesaw/scrollable (seesaw/listbox :model (:todo-files @next-image-state)) :id "tts"))



(:current-selected @next-image-state)

@next-image-state

(:todo-files @next-image-state)
