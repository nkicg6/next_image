;; playing with layout of gui
(ns next-image.gui-testing
  (:require [seesaw.bind :as b]
            [seesaw.core :as seesaw]
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


(defn make-scrollbox [model id]
  "Make generic scollable scrollbox."
  (seesaw/scrollable (seesaw/listbox :model model
                                     :id id)))

(defn update-selection! [selected-file]
  "update selected file in state atom"
  (swap! next-image-state assoc :current-selected selected-file))

(defn main-view  [master-frame]
  (seesaw/config! master-frame :content
                  (seesaw/top-bottom-split
                   (seesaw/tabbed-panel :tabs
                                        [{:title "todo..." :content
                                          (make-scrollbox (:todo-files @next-image-state) "todos")}
                                         {:title "done" :content
                                          (make-scrollbox (:done-files @next-image-state) "dones")}])
                   (seesaw/border-panel :north (seesaw/left-right-split (seesaw/label :text "current image: ")
                                                                        (seesaw/label :text ""
                                                                                      :id "currentselection"))
                                        :center (seesaw/label :text "metadata-here")
                                        :south (seesaw/left-right-split
                                                (seesaw/button :text "choose a different project")
                                                (seesaw/button :text "Next image"))
                                       :hgap 5 :vgap 10 :border 10))))

(def test-frame
  (seesaw/frame :title "base frame" :width 400 :height 800
                :content (seesaw/label :text "testing")))

(-> test-frame
    main-view
    seesaw/show!)

;; this is how you add listeners based on a widget's :id

(seesaw/listen
 (seesaw/select test-frame [:#todos])
 :selection (fn [e] (update-selection! (seesaw/selection e))))

(seesaw/listen
 (seesaw/select test-frame [:#todos])
 :selection (fn [e] (seesaw/config!
                    (seesaw/select test-frame [:#currentselection]) :text (str (seesaw/selection e)))))



(:current-selected @next-image-state)

@next-image-state

(:todo-files @next-image-state)

