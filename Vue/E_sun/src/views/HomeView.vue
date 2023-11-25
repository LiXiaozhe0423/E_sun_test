<template>
  <div>
    <div class="album py-5 bg-body-tertiary">
      <div class="container">

        <H1>
          座位選擇
        </H1>

        <!--產生人員-->
        <select class="form-select" aria-label="Default select example" v-model="selectedEmpId"
          style=" max-width: 300px;">
          <option selected disabled>請選擇人員</option>
          <option :value="emp.empId" v-for="emp in allEmp" :key="emp.empId">
            {{ emp.floorSeatSeq ? `${emp.name} (已有坐位)` : emp.name }}
          </option>
        </select>
        <br>

        <!--產生座位-->
        <div class="row row-cols-1 row-cols-sm-2 row-cols-md-4 g-3">
          <div v-for="chart in allChart" :key="chart.floorSeatSeq" class="chart card"
            :style="{ width: '18rem', backgroundColor: seatColor(chart) }" @click="checkSeat(chart)">
            {{ chart.floorNo }}樓: 座位{{ chart.seatNo }}
            <span v-if="empWithSeat(chart) !== null">員工編號: {{ empWithSeat(chart) }}</span>
            <span v-else>未分配</span>
          </div>
        </div>
      </div>

      <br>

      <!--表示顏色-->
      <table style="margin-left: 10px;">
        <ul>
          <li><span class="color-box white"></span>未分配</li>
          <li><span class="color-box green"></span>選擇中</li>
          <li><span class="color-box red"></span>已分配</li>
        </ul>
      </table>
      <button type="button" class="btn btn-outline-primary" @click="cleanChart(selectedEmpId)">清除</button>
      <button type="button" class="btn btn-outline-primary" style="margin-left: 5px;"
        @click="choseChart(selectedEmpId, selectedSeat.floorSeatSeq)">送出</button>

    </div>



  </div>
</template>

<script setup>
import { ref, onBeforeMount } from 'vue'
import axios from 'axios';

// 全部的座位資訊
const allChart = ref([]);
// 全部人員資訊
const allEmp = ref([]);
// 被選定的座位
const selectedSeat = ref(null);


// 取得座位資訊
const getAllChart = async () => {
  const URL = `http://localhost:8080/ShowChart`
  const response = await axios.get(URL, {
    withCredentials: true,
  });

  allChart.value = response.data.map(chart => ({ ...chart, backgroundColor: empWithSeat(chart) !== null ? '#FF7575' : 'white' }));
  console.log(response.data);
}

// 取得人員資訊
const getAllEmp = async () => {
  const URL = `http://localhost:8080/ShowEmp`
  const response = await axios.get(URL, {
    withCredentials: true,
  })
  allEmp.value = response.data
  console.log(response.data)
}

// 判斷該座位是否有對應的員工編號
const empWithSeat = (chart) => {
  const foundEmp = allEmp.value.find(emp => emp.floorSeatSeq == chart.floorSeatSeq);
  return foundEmp ? foundEmp.empId : null;
}

// 點擊座位，進行驗證
const checkSeat = (chart) => {
  // 將已選定的座位重置為紅色或白色
  if (selectedSeat.value !== null) {
    const prevSeatIndex = allChart.value.findIndex(item => item.floorSeatSeq === selectedSeat.value.floorSeatSeq);
    if (prevSeatIndex !== -1) {
      allChart.value[prevSeatIndex].backgroundColor = empWithSeat(selectedSeat.value) !== null ? '#FF7575' : 'white';
    }
  }

  if (empWithSeat(chart) !== null) {
    alert('此坐位有人');
  } else {
    const index = allChart.value.findIndex(item => item.floorSeatSeq === chart.floorSeatSeq);
    if (index !== -1) {
      allChart.value[index].backgroundColor = '#4EFEB3'; // 更新資料，改變背景顏色
      selectedSeat.value = chart; // 設置新選定的座位
    }
  }

}

// 設定座位的背景色
const seatColor = (chart) => {
  return chart.backgroundColor || 'white';
}

// 清除坐位
const cleanChart = async (selectedEmpId) => {
  if (selectedEmpId == null) {
    alert('請先選擇人員');
  } else {
    const URL = `http://localhost:8080/selectChart/${selectedEmpId}?seatSeq=0`;
    try {
      await axios.put(URL, {
        withCredentials: true,
        cache: false,
      });

    } catch (error) {
      console.log("更改失敗")
    }
    window.location.reload();
  }
};

// 確定選擇坐位
const choseChart = async (selectedEmpId, selectedSeat) => {
  if (selectedEmpId == null) {
    alert('請先選擇人員');
  } else if (selectedSeat == null) {
    alert('請先選擇坐位');
  } else {
    const URL = `http://localhost:8080/selectChart/${selectedEmpId}?seatSeq=${selectedSeat}`;
    try {
      await axios.put(URL, {
        withCredentials: true,
        cache: false,
      });
    } catch (error) {
      console.log("更改失敗")
    }
    window.location.reload();
  }
};


onBeforeMount(async () => {
  await getAllEmp();
  await getAllChart();
});
</script>

<style scoped>
.chart {
  border-radius: 5px;
  text-align: center;
  margin: auto;
}

.color-box {
  width: 35px;
  height: 35px;
  display: inline-block;
  margin-right: 5px;
  border-radius: 5px;
}

.red {
  background-color: #FF7575;
}

.green {
  background-color: #4EFEB3;
}

.white {
  background-color: white;
}

ul {
  list-style-type: none;
  padding-left: 0;
}

h1 {
  margin: auto;
}
</style>
