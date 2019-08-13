import { Component, OnInit, Input, ViewChild, ElementRef, OnChanges } from '@angular/core';
import { RestService } from '../rest.service';
import * as d3 from 'd3';
import * as $ from 'jquery';
//declare var M: any;

@Component({
  selector: 'app-hashtags',
  templateUrl: './hashtags.component.html',
  styleUrls: ['./hashtags.component.css']
})
export class HashtagsComponent implements OnInit {

  hashtags: any = undefined;
  term: string;
  notHashtagData: boolean;
  notData: boolean;

  @ViewChild('barChart')
  private chartContainer: ElementRef;
  @ViewChild('termInput') 
  private termInput: ElementRef;

  constructor(public rest: RestService) { }

  ngOnInit() {
    this.notHashtagData = true;
    this.notData = false;
    //this.getHashtags('amor');
  }

  public getHashtags() {
    this.hashtags = [];
    console.log(this.termInput.nativeElement.value);
    this.rest.getTopHashtags(this.termInput.nativeElement.value).subscribe((data: {}) => {
        console.log(data);
        this.hashtags = data;
      if(this.hashtags.length > 0){ 
        this.createChart();
        this.notHashtagData = false;
        this.notData = false;
      } else {
        this.notHashtagData = true;
        this.notData = false;
      } 
    });
  }

  private createChart(): void {
    d3.select('svg').remove();

    const element = this.chartContainer.nativeElement;
    const data = this.hashtags;

    var margin = { top: 10, right: 20, bottom: 90, left: 30 },
      width = 800 - margin.left - margin.right,
      height = 450 - margin.top - margin.bottom;

    // append the svg object to the body of the page
    var svg = d3.select(element)
      .append("svg")
      .attr("width", width + margin.left + margin.right)
      .attr("height", height + margin.top + margin.bottom)
      .append("g")
      .attr("transform",
        "translate(" + margin.left + "," + margin.top + ")");

    // X axis
    var x = d3.scaleBand()
      .range([0, width])
      .domain(data.map(function (d) { return d.name; }))
      .padding(0.2);
    svg.append("g")
      .attr("transform", "translate(0," + height + ")")
      .call(d3.axisBottom(x))
      .selectAll("text")
      .attr("transform", "translate(-10,0)rotate(-45)")
      .style("text-anchor", "end");

    // Add Y axis
    var y = d3.scaleLinear()
      .domain([0, 100])
      .range([height, 0]);
    svg.append("g")
      .call(d3.axisLeft(y));

    // Bars
    svg.selectAll("mybar")
      .data(data)
      .enter()
      .append("rect")
      .attr("x", function (d) { return x(d.name); })
      .attr("width", x.bandwidth())
      .attr("fill", "#3399ff")
      // no bar at the beginning thus:
      .attr("height", function (d) { return height - y(0); }) // always equal to 0
      .attr("y", function (d) { return y(0); })

    // Animation
    svg.selectAll("rect")
      .transition()
      .duration(800)
      .attr("y", function (d) { return y(d.value); })
      .attr("height", function (d) { return height - y(d.value); })
      .delay(function (d, i) { return (i * 100) })

  }
}
